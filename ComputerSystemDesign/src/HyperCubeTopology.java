/**
 * Created by oleh on 07.11.16.
 */
public class HyperCubeTopology extends Topology {

    private static int[][] cluster = {
            {0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1},
            {0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0}
    };

    private int[][] interClusterConn = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0}};

    private int topologyOrder;
    private int [][] empty = new int[1][1];

    public HyperCubeTopology(int topologyOrder) {
        super(cluster.length, (int)Math.pow(2, topologyOrder));
        this.topologyOrder = topologyOrder;
    }

    @Override
    int[][] generateTopology() {
        if (topologyOrder == 0) {
            return cluster;
        } else {
            return generateRec(0, empty);
        }
    }

    private int[][] generateRec(int currentOrder, int[][] top) {
        if (currentOrder == 0) {
            return generateRec(currentOrder + 1, cluster);
        } else if (currentOrder == 1) {
            int[][] topologySt = new int[clusterNodes * 2][clusterNodes * 2];
            copyMatrix(topologySt, top, 0, 0);
            copyMatrix(topologySt, top, top.length, top.length);
            copyMatrix(topologySt, interClusterConn, 0, clusterNodes);
            copyMatrix(topologySt, transpose(interClusterConn), clusterNodes, 0);

            return generateRec(currentOrder + 1, topologySt);
        } else if (currentOrder > topologyOrder) {
            return top;
        } else {
            int[][] topologyStep = new int[top.length * 2][top.length * 2];
            copyMatrix(topologyStep, top, 0, 0);
            copyMatrix(topologyStep, top, top.length, top.length);

            int[][] interPartConnections = new int[top.length][top.length];

            for (int i = 0; i < interPartConnections.length; i++) {
                interPartConnections [i][i] = 1;
            }

            copyMatrix(topologyStep, interPartConnections, 0, top.length);
            copyMatrix(topologyStep, interPartConnections, top.length, 0);

            return generateRec(currentOrder + 1, topologyStep);
        }
    }
}
