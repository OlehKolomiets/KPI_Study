/**
 * Created by oleh on 16.10.16.
 */
public abstract class Topology {
    protected int clusterNodes;
    protected int clusterCount;
    protected int numberOfElements;
    protected int[][] topology;

    public Topology(int clusterNodes, int clusterCount) {
        this.clusterNodes = clusterNodes;
        this.clusterCount = clusterCount;
        numberOfElements = clusterCount * clusterNodes;
        topology = new int[numberOfElements][numberOfElements];
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    protected void copyMatrix(int[][] dst, int[][]src, int startI, int startJ) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dst[startI + i], startJ, src.length);
        }
    }

    abstract int[][] generateTopology();

    protected int[][] transpose(int[][] src) {
        int[][] result = new int[src[0].length][src.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[1].length; j++) {
                result[j][i] = src[i][j];
            }
        }
        return result;
    }

    protected int[][] mergeMatrix(int[][] first, int[][] second) throws IllegalArgumentException {
        if (first.length != second.length || first[0].length != second[0].length) {
            throw new IllegalArgumentException("matrix should have the same size");
        }

        int[][] result = new int[first.length][first[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = first[i][j] | second[i][j];
            }
        }
        return result;
    }
}
