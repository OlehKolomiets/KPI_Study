import core.TopologyAnalyzer;
import coursework.GreedTopology;

/**
 * Created by oleh on 16.10.16.
 */
public class Main {
    public static void main(String... args) {

        long time = System.currentTimeMillis();
        for (int i = 1; i < 10; i++) {
//            HyperCubeTopology topology = new HyperCubeTopology(i);
//            HyperCubeTopology topology = new HyperCubeTopology(i);
            GreedTopology topology = new GreedTopology((int) Math.pow(i, 2));
            TopologyAnalyzer topologyAnalyzer =
                    new TopologyAnalyzer(topology.getNumberOfElements(), topology.generateTopology());
            System.out.println("\nnumber of processors : " + topology.getNumberOfElements());
            System.out.println("diameter : " + topologyAnalyzer.getDiameter());
            System.out.println("avgDiameter : " + topologyAnalyzer.getAvgDiameter());
            System.out.println("degree : " + topologyAnalyzer.getTopologyDegree());
            System.out.println("cost : " + topologyAnalyzer.getCost());
            System.out.println("traffic : " + topologyAnalyzer.getTopologyTraffic());
            System.out.println("time : " + (System.currentTimeMillis() - time));
//            printMatrix(topology.generateTopology(), topology.getClusterNodes());
        }
    }
}
