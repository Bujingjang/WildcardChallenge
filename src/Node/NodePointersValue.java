package Node;

import java.util.ArrayList;

import Utility.Utility;

public class NodePointersValue {

    private int index;
    private ArrayList<Short> charLocs;

    public NodePointersValue (int index, short charLoc) {
        this.index = index;
        this.charLocs = new ArrayList<>();
        this.charLocs.add(charLoc);
    }

    public int getIndex () {
        return this.index;
    }

    public Short getCharLocsAtIndex (int i) {
        return this.charLocs.get(i);
    }

    public void addCharLocs (short charLoc) {
        this.charLocs.add(charLoc);
    }

    public boolean findCharLoc (short target) {
        if (Utility.binarySearch(this.charLocs, target, (short) 0, (short) (this.charLocs.size() - 1)) != -1) {
            return true;
        }
        else {
            return false;
        }
    }

    public Short findMinimumCharLoc (short baseNum) {

        for (int i = 0; i < this.charLocs.size(); i++) {
            if (this.charLocs.get(i) > baseNum) {
                return this.charLocs.get(i);
            }
        }

        return (short) -1;

    }

}