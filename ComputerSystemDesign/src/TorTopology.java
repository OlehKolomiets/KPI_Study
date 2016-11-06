/**
 * Created by oleh on 16.10.16.
 */
public class TorTopology extends Topology {

    private int topologyOrder;
    private int[][] cluster = {
            {0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0}};
    private int[][] horizontalClusterConnections = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0}};
    private int[][] verticalClusterConnections = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0}};

    public TorTopology(int clusterCount) {
        super(8, clusterCount);
        topologyOrder = (int) Math.sqrt(clusterCount);
    }

    @Override
    public int[][] generateTopology() {

        for (int i = 0; i < clusterCount; i++) {
            copyMatrix(topology, cluster, i * clusterNodes, i * clusterNodes);
        }

        if (topologyOrder == 1)
            return topology;

        if (topologyOrder == 2) {
            horizontalClusterConnections =
                    mergeMatrix(horizontalClusterConnections, transpose(horizontalClusterConnections));
            verticalClusterConnections =
                    mergeMatrix(verticalClusterConnections, transpose(verticalClusterConnections));
        }

        for (int i = 0; i < topologyOrder; i++) {
            buildHorizontalConnection(i, horizontalClusterConnections, transpose(horizontalClusterConnections));
            buildVerticalConnection(i, verticalClusterConnections, transpose(verticalClusterConnections));
        }

        return topology;
    }


    private void buildHorizontalConnection(int index, int[][] nextConnMatrix, int [][] prevConnMatrix) {
        for (int i = 0; i < topologyOrder; i++) {

            int startPreviousCon = (index * topologyOrder + i - 1) * clusterNodes;
            int startNextCon = (index * topologyOrder + i + 1) * clusterNodes;

            if (i == 0) {
                startPreviousCon = ((index + 1) * topologyOrder - 1) * clusterNodes;
            } else if (i == topologyOrder - 1) {
                startNextCon = index * topologyOrder * clusterNodes;
            }

            copyMatrix(topology, nextConnMatrix, (index * topologyOrder + i) * clusterNodes, startNextCon);
            copyMatrix(topology, prevConnMatrix, (index * topologyOrder + i) * clusterNodes, startPreviousCon);
        }
    }

    private void buildVerticalConnection(int index, int[][] nextConnMatrix, int[][] prevConnMatrix) {
        for (int i = 0; i < topologyOrder; i++) {

            int startPreviousCon = ((i - 1) * topologyOrder + index) * clusterNodes;
            int startNextCon = (index + (i + 1) * topologyOrder) * clusterNodes;
            if (i == 0) {
                startPreviousCon = (clusterCount - (topologyOrder - index)) * clusterNodes;
            } else if (i == topologyOrder - 1) {
                startNextCon = index * clusterNodes;
            }

            copyMatrix(topology, nextConnMatrix, startNextCon, (index + i * topologyOrder) * clusterNodes);
            copyMatrix(topology, prevConnMatrix, startPreviousCon, (index + i * topologyOrder) * clusterNodes);
        }
    }
}
