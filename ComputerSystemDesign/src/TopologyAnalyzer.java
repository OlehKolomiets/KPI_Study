/**
 * Created by oleh on 16.10.16.
 */
public class TopologyAnalyzer {
    private int[][] adjacencyMatrix;
    private int[][] distances = null;
    private Integer topologyDegree = null;
    private Integer diameter = null;
    private Double avgDiameter = null;
    private Double topologyTraffic = null;
    private Integer cost = null;
    private int N;

    public TopologyAnalyzer(int N, int[][] topology) throws IllegalArgumentException {
        if (topology.length == topology[1].length && topology.length == N) {
            this.N = N;
            adjacencyMatrix = topology;
        } else {
            throw new IllegalArgumentException("topology matrix should be N x N");
        }
    }

    private void calculateTopologyDegree() {
        topologyDegree = 0;
        for (int i = 0; i < N; i++) {
            int localDegree = 0;
            for (int j = 0; j < N; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    localDegree++;
                }
            }
            if (topologyDegree < localDegree) {
                topologyDegree = localDegree;
            }
        }
    }

    private void calculateDiameter() {
        if (distances == null) {
            calculateDistances();
        }

        diameter = 0;
        for (int[] distancesVector : distances) {
            for (int distance : distancesVector) {
                if (distance > diameter) {
                    diameter = distance;
                }
            }
        }
    }

    private void calculateDistances() {
        initializeDistances();
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    distances[i][j] = (int) Math.min((long) distances[i][j], ((long) distances[i][k] + distances[k][j]));
                }
            }
        }
    }

    private void initializeDistances() {
        distances = new int[N][N];
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[1].length; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = adjacencyMatrix[i][j] == 1 ? 1 : Integer.MAX_VALUE;
                }
            }
        }
    }

    private void calculateAvgDiameter() {
        int sum = 0;
        if (distances == null) {
            calculateDistances();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += distances[i][j];
            }
        }
        avgDiameter = (1.0 * sum) / (N * (N - 1));
    }

    private void calculateCost() {
        if (diameter == null) {
            calculateDiameter();
        }
        if (topologyDegree == null) {
            calculateTopologyDegree();
        }
        cost = diameter * N * topologyDegree;
    }

    private void calculateTraffic() {
        if (avgDiameter == null) {
            calculateAvgDiameter();
        }
        if (topologyDegree == null) {
            calculateTopologyDegree();
        }

        topologyTraffic = 2 * avgDiameter / topologyDegree;
    }

    public Integer getTopologyDegree() {
        if (topologyDegree == null) {
            calculateTopologyDegree();
        }
        return topologyDegree;
    }

    public Integer getDiameter() {
        if (diameter == null) {
            calculateDiameter();
        }
        return diameter;
    }

    public Double getAvgDiameter() {
        if (avgDiameter == null) {
            calculateAvgDiameter();
        }
        return avgDiameter;
    }

    public Double getTopologyTraffic() {
        if (topologyTraffic == null) {
            calculateTraffic();
        }
        return topologyTraffic;
    }

    public Integer getCost() {
        if (cost == null) {
            calculateCost();
        }
        return cost;
    }

    public int[][] getDistances() {
        return distances;
    }
}
