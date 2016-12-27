package coursework;

import java.util.List;

/**
 * Created by oleh on 13.12.16.
 */
public class Node {
    private int generalIndex;
    private int clusterInnerIndex;
    private int clusterIndex;
    private List<Integer> connectedNodeIndexes;
    private boolean isBroken;

    public Node(int generalIndex, int clusterInnerIndex, int clusterIndex, List<Integer> connectedNodeIndexes) {
        this.generalIndex = generalIndex;
        this.clusterInnerIndex = clusterInnerIndex;
        this.clusterIndex = clusterIndex;
        this.connectedNodeIndexes = connectedNodeIndexes;
        this.isBroken = false;
    }

    public int getGeneralIndex() {
        return generalIndex;
    }

    public int getClusterInnerIndex() {
        return clusterInnerIndex;
    }

    public int getClusterIndex() {
        return clusterIndex;
    }

    public List<Integer> getConnectedNodeIndexes() {
        return connectedNodeIndexes;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public boolean isBroken() {
        return isBroken;
    }
}
