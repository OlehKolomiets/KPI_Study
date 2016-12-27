import core.Topology;

/**
 * Created by oleh on 16.10.16.
 */
public class CircleTopology extends Topology {

    public CircleTopology(int clusterCount) {
        super(7, clusterCount);
    }

    private int[][] cluster = {
            {0, 1, 1, 1, 0, 0, 0},
            {1, 0, 1, 0, 1, 0, 0},
            {1, 1, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 1, 1, 1},
            {0, 1, 0, 1, 0, 1, 1},
            {0, 0, 1, 1, 1, 0, 1},
            {0, 0, 0, 1, 1, 1, 0}};

    private int[][] previousClusterConnections = {
            {1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1}};

    private int[][] nextClusterConnections = {
            {1, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1}};

    @Override
    public int[][] generateTopology() {
        if (clusterCount == 1) {
            copyMatrix(topology, cluster, 0, 0);
        } else {
            for (int i = 0; i < clusterCount; i++) {

                int startJPreviousCon = (i-1)* clusterNodes;
                int startJNextCon = (i+1)* clusterNodes;
                if (i == 0) {
                    startJPreviousCon = (clusterCount -1)* clusterNodes;
                } else if (i == clusterCount -1) {
                    startJNextCon = 0;
                }

                copyMatrix(topology, cluster, i* clusterNodes, i* clusterNodes);
                copyMatrix(topology, nextClusterConnections, i* clusterNodes, startJNextCon);
                copyMatrix(topology, previousClusterConnections, i* clusterNodes, startJPreviousCon);
            }
        }

        return topology;
    }
}
