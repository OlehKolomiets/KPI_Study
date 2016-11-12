/**
 * Created by oleh on 16.10.16.
 */
public class Main {
    public static void main(String... args) {

        long time = System.currentTimeMillis();
        for (int i = 0; i < 6; i++) {
            HyperCubeTopology topology = new HyperCubeTopology(i);
            TopologyAnalyzer topologyAnalyzer =
                    new TopologyAnalyzer(topology.getNumberOfElements(), topology.generateTopology());
            System.out.println("\nnumber of processors : " + topology.getNumberOfElements());
            System.out.println("degree : " + topologyAnalyzer.getTopologyDegree());
            System.out.println("diameter : " + topologyAnalyzer.getDiameter());
            System.out.println("avgDiameter : " + topologyAnalyzer.getAvgDiameter());
            System.out.println("cost : " + topologyAnalyzer.getCost());
            System.out.println("traffic : " + topologyAnalyzer.getTopologyTraffic());
            System.out.println("time : " + (System.currentTimeMillis() - time));
//            printMatrix(topology.generateTopology(), topology.clusterNodes);
        }
    }

    public static void printMatrix(int[][] m, int N) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[1].length; j++) {
                if (j != m[1].length - 1) {
                    System.out.print(String.format("% 2d", m[i][j]) + ( j % N == N-1 ? " " : ""));
                } else {
                    System.out.println(String.format("% 2d", m[i][j]));
                }
            }
            if ( i % N == N-1) {
                System.out.println("");
            }
        }
    }
}
