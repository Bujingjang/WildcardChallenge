package Node;

import java.util.*;

public class NodePointers {

	// OLD ONE

	// private HashMap<Integer, ArrayList<Integer>> arrayPointers;
	
	// public NodePointers () {
	// 	this.arrayPointers = new HashMap<>();
	// }
	
	// public void insertKeyIndex (int idx, int charLoc) {
		
	// 	if (!arrayPointers.containsKey(idx)) {
	// 		this.arrayPointers.put(idx, new ArrayList<>());
	// 	}
		
	// 	this.arrayPointers.get(idx).add(charLoc);
	// }
	
	// public ArrayList<Integer> retrieveCharLocsAtIndex (int idx) {
	// 	return this.arrayPointers.get(idx);
	// }
	
	// public HashMap<Integer, ArrayList<Integer>> getArrayPointers () {
	// 	return this.arrayPointers;
	// }
	
	// public Set<Integer> getKeySet () {
	// 	return this.arrayPointers.keySet();
	// }
	
	private LinkedList<NodePointersValue> arrayPointers;
	
	public NodePointers () {
        this.arrayPointers = new LinkedList<>();
    }

	public void insertKeyIndex (int idx, short charLoc) {

		if (this.arrayPointers.isEmpty() || this.arrayPointers.getLast().getIndex() != idx) {
			this.arrayPointers.addLast(new NodePointersValue(idx, charLoc));
		}
		else {
			this.arrayPointers.getLast().addCharLocs(charLoc);
		}

	}

	public Iterator<NodePointersValue> getArrayPointersIterator () {
		return this.arrayPointers.iterator();
	}

}
