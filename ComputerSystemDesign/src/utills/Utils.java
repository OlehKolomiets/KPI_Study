package utills;

import coursework.Node;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by oleh on 27.12.16.
 */
public class Utils {
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

    public static void printRoutePair(List<Node> nodes, Pair<Integer, Integer> pair) {
        Node source = nodes.get(pair.getKey());
        Node dest = nodes.get(pair.getValue());
        System.out.println(String.format("%s -> %s",
                formatNode(source),
                formatNode(dest)));
    }

    public static String formatNode(Node node) {
        return String.format("%d [%d.%d]",
                node.getGeneralIndex(),
                node.getClusterIndex(),
                node.getClusterInnerIndex());
    }
}
