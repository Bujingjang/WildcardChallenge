package Node;

import java.util.ArrayList;
import java.util.HashMap;

public interface NodeInterface {
	
	public void addIndexPointerBetweenWildcard(int idx, int charLoc);
	
	public void addIndexPointerAfterWildcard(int idx, int charLoc);
	
	public void addIndexPointerBeforeWildcard(int idx, int charLoc);
	
	public void addIndexPointerNoWildcard(int idx, int charLoc);
	
	public Node getChild (char character);
	
	public boolean isLeaf ();
	
	public NodePointers retrieveArrayPointerBetweenWildcard ();
	
	public NodePointers retrieveArrayPointerAfterWildcard ();
	
	public NodePointers retrieveArrayPointerBeforeWildcard ();
	
	public NodePointers retrieveArrayPointerNoWildcard ();

}
