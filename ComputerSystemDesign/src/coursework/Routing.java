package coursework;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by oleh on 13.12.16.
 */
public class Routing {

    public static List<List<Pair<Integer, Integer>>> oneToAll(List<Node> nodes, int startNodeIndex) {
        List<List<Pair<Integer, Integer>>> routingSteps = new ArrayList<>();
        List<Integer> receivedDataNodes = new ArrayList<>();
        receivedDataNodes.add(startNodeIndex);
        List<Integer> brokenNodes = nodes.stream()
                .filter(Node::isBroken)
                .map(Node::getGeneralIndex)
                .collect(Collectors.toList());
        long brokenNodesCount = brokenNodes.size();
        while (receivedDataNodes.size() < nodes.size() - brokenNodesCount) {
            List<Pair<Integer, Integer>> nextStep = getNextStepTransmissions(receivedDataNodes, nodes);
            if (!nextStep.isEmpty()) {
                routingSteps.add(nextStep);
            } else {
                List<Integer> unachievableNodes = nodes.stream()
                        .filter(node -> !receivedDataNodes.contains(node.getGeneralIndex())
                                && !brokenNodes.contains(node.getGeneralIndex()))
                        .map(Node::getGeneralIndex)
                        .collect(Collectors.toList());
                System.out.print("there are unachievable nodes:");
                for (Integer unachievableNode : unachievableNodes) {
                    System.out.print(String.format(" %d", unachievableNode));
                }
                System.out.println("\n");
                break;
            }
        }
        return routingSteps;
    }

    private static List<Pair<Integer, Integer>> getNextStepTransmissions(List<Integer> receivedDataNodes, List<Node> nodes) {
        List<Integer> receivedDataNodesCopy = new ArrayList<>(receivedDataNodes);
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for (Integer nodeIndex : receivedDataNodesCopy) {
            Node currentNode = nodes.get(nodeIndex);
            List<Integer> connectedNodeIndexes = currentNode.getConnectedNodeIndexes();
            Collections.sort(connectedNodeIndexes, (o1, o2) -> {
                int currentCluster = currentNode.getClusterIndex();
                Node first = nodes.get(o1);
                Node second = nodes.get(o2);
                if (first.getClusterIndex() == currentCluster &&
                        second.getClusterIndex() == currentCluster) {
                    if (first.getConnectedNodeIndexes().size() == second.getConnectedNodeIndexes().size()) {
                        return 0;
                    } else {
                        return first.getConnectedNodeIndexes().size() > second.getConnectedNodeIndexes().size() ? -1
                                : 1;
                    }
                } else {
                    if (first.getClusterIndex() != currentCluster && second.getClusterIndex() != currentCluster) {
                        // compare connections
                        return first.getConnectedNodeIndexes().size() > second.getConnectedNodeIndexes().size() ? -1
                                : 1;
                    } else if (first.getClusterIndex() != currentCluster) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            });

            for (Integer index : connectedNodeIndexes) {
                if (!receivedDataNodes.contains(index) && !nodes.get(index).isBroken()) {
                    result.add(new Pair<>(nodeIndex, index));
                    receivedDataNodes.add(index);
                    break;
                }
            }
        }
        return result;
    }
}
