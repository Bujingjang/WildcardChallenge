package Node;

import java.util.ArrayList;
import java.util.HashMap;

/*
 *  Node Class
 *  
 *  This class represents each node in a tree and it contains a children in a HashMap data type to represent
 *  each child (in characters) and get easy access to it. Also, in each node, it has properties to store
 *  index pointers that references to the real data. The properties has Between Wildcard (*a*), After Wildcard (*a),
 *  Before Wildcard (a*), and No Wildcard (a). This properties represents the wildcard on a particular string when
 *  traversing through the characters and it builds up a string.
 */
public class Node {
	
	private HashMap<Character, Node> children;
	private NodePointers betweenWildcardPtr; // *a*
	private NodePointers afterWildcardPtr; // *a
	private NodePointers beforeWildcardPtr; // a*
	private NodePointers noWildcardPtr; // a
	
	public Node() {

	}
	
	// Adding the index pointer in Between Wildcard Property
	public void addIndexPointerBetweenWildcard(int idx, short charLoc) {
		
		// If Between Wildcard pointer has not been initialized yet
		if (betweenWildcardPtr == null) {
			betweenWildcardPtr = new NodePointers();
		}
		
		// Add the key index and the starting suffix index
		betweenWildcardPtr.insertKeyIndex(idx, charLoc);
		
	}
	
	// Adding the index pointer in After Wildcard Property
	public void addIndexPointerAfterWildcard(int idx, short charLoc) {
		
		// If After Wildcard Pointer has not been initialized yet
		if (afterWildcardPtr == null) {
			afterWildcardPtr = new NodePointers();
		}
		
		// Add the key index and the starting suffix index
		afterWildcardPtr.insertKeyIndex(idx, charLoc);
		
	}
	
	// Adding the index pointer in Before Wildcard Property
	public void addIndexPointerBeforeWildcard(int idx, short charLoc) {
		
		// If Before Wildcard Pointer has not been initialized yet
		if (beforeWildcardPtr == null) {
			beforeWildcardPtr = new NodePointers();
		}
		
		// Add the key index and the starting suffix index
		beforeWildcardPtr.insertKeyIndex(idx, charLoc);
		
	}
	
	// Adding the index pointer in No Wildcard Property
	public void addIndexPointerNoWildcard(int idx, short charLoc) {
		
		// If No Wildcard Pointer has not been initialized yet
		if (noWildcardPtr == null) {
			noWildcardPtr = new NodePointers();
		}
		
		// Add the key index and the starting suffix index
		noWildcardPtr.insertKeyIndex(idx, charLoc);
		
	}
	
	// Add children (connect the child node to its parent)
	public void addChild (char character, Node child) {
		
		// If there are no children yet (that node is a leaf)
		if (isLeaf()) {
			this.children = new HashMap<>();
		}
		
		// Add an entry to the child, with the character as the key and the child node as the pointer
		this.children.put(character, child);
	}
	
	// Retrieve a particular child (using the character key)
	public Node getChild (char character) {
		return this.children.get(character);
	}
	
	// Check if the node has a particular child (using the character key)
	public boolean checkChild (char character) {
		return this.children.containsKey(character);
	}
	
	// CHeck if the node is the leaf (no children)
	public boolean isLeaf () {
		return this.children == null;
	}
	
	// Retrieve the Node Pointer of the Between Wildcard Property
	public NodePointers retrieveArrayPointerBetweenWildcard () {
		return this.betweenWildcardPtr;
	}
	
	// Retrieve the Node Pointer of the After Wildcard Property
	public NodePointers retrieveArrayPointerAfterWildcard () {
		return this.afterWildcardPtr;
	}
	
	// Retrieve the Node Pointer of the Before Wildcard Property
	public NodePointers retrieveArrayPointerBeforeWildcard () {
		return this.beforeWildcardPtr;
	}
	
	// Retrieve the Node Pointer of the No Wildcard Property
	public NodePointers retrieveArrayPointerNoWildcard () {
		return this.noWildcardPtr;
	}
	
}



















