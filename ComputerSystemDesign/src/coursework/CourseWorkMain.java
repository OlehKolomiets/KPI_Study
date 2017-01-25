package coursework;

import javafx.util.Pair;
import utills.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 13.12.16.
 */
public class CourseWorkMain {
    public static void main(String[] args) {
        GreedTopology topology = new GreedTopology(1);
        int[][] topologyMatrix = topology.generateTopology();
        List<Node> nodes = TopologyUtils.getNodes(topologyMatrix, topology.getClusterNodes());
        List<Integer> brokenNodes = Arrays.asList();
        nodes.stream()
                .filter(node -> brokenNodes.contains(node.getGeneralIndex()))
                .forEach(node -> node.setBroken(true));
        int startNode = 3;
        if (brokenNodes.contains(startNode)) {
            System.out.println("Start node is broken");
        } else {
            List<List<Pair<Integer, Integer>>> steps = Routing.oneToAll(nodes, 3);
            for (int i = 0; i < steps.size(); i++) {
                List<Pair<Integer, Integer>> step = steps.get(i);
                System.out.println("Step " + i + " : ");
                step.forEach(pair -> Utils.printRoutePair(nodes, pair));
            }
        }
    }
}
