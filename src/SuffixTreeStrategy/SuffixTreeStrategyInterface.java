package SuffixTreeStrategy;

import java.util.ArrayList;

public interface SuffixTreeStrategyInterface {

	void constructDataStructure(ArrayList<String> strings);
	
	ArrayList<String> searchDataStructure(String wildcard);
	
}
