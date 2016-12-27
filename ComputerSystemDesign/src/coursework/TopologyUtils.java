package coursework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh on 13.12.16.
 */
public class TopologyUtils {

    public static List<Node> getNodes(int [][] topology, int clusterSize) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < topology.length; i++) {
            int clusterIndex = i / clusterSize;
            int nodeIndex = i - clusterIndex * clusterSize;
            Node node = new Node(i, nodeIndex, clusterIndex, new ArrayList<>());
            nodes.add(node);
        }

        for (int i = 0; i < topology.length; i++) {
            for (int j = 0; j < topology[i].length; j++) {
                if (topology[i][j] == 1) {
                    nodes.get(i).getConnectedNodeIndexes().add(nodes.get(j).getGeneralIndex());
                }
            }
        }

        return nodes;
    }
}
