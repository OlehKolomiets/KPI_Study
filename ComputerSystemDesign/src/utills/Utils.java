package utills;

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
}
