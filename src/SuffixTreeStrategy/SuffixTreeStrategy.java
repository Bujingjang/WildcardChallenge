package SuffixTreeStrategy;

import java.util.*;
import java.io.*;

import Node.*;
import Utility.*;
import Record.Record;

public class SuffixTreeStrategy {
	
	private Node root;
	private HashMap<Integer, String> stringsIndexTable;
	private int currKey = -1;

	private final byte FIXED_N_GRAMS_VALUE = 2;
	
	public SuffixTreeStrategy () {
		this.root = new Node();
		this.stringsIndexTable = null;
	}
	
	// Test Function
	public Node getNode() {
		return this.root;
	}
	
	public void constructDataStructureWithoutPartition (ArrayList<String> strings) {
		
 		this.stringsIndexTable = Utility.generateHashmap(strings);
		Node ptr = this.root;

 		for (int i = 0; i < strings.size(); i++) {
			
 			String currStr = strings.get(i);
 			// System.out.println("String " + i);
			
 			for (short j = 0; j < currStr.length(); j++) {
				
				int currKey = i;
 				short currStartIdx = j;
 				String currSuffix = currStr.substring(j);
				
 				for (int k = 0; k < currSuffix.length(); k++) {
					
 					char currChar = currSuffix.charAt(k);
					
 					// Check if there is no child for that character, create a node for that character
 					if (ptr.isLeaf() || !ptr.checkChild(currChar)) {
 						Node newChild = new Node();
 						ptr.addChild(currChar, newChild);
 					}
 					ptr = ptr.getChild(currChar);
					
 					// Prompt that current character into the corresponding array pointers
					if (currStartIdx != 0 || k != currSuffix.length() - 1) {
 						ptr.addIndexPointerBetweenWildcard(currKey, currStartIdx);
					}
					
 					if (currStartIdx == 0) {
 						ptr.addIndexPointerBeforeWildcard(currKey, currStartIdx);
 					}
					
 					if (k == currSuffix.length() - 1) {
 						ptr.addIndexPointerAfterWildcard(currKey, currStartIdx);
 					}
					
 					if (currStartIdx == 0 && k == currSuffix.length() - 1) {
 						ptr.addIndexPointerNoWildcard(currKey, currStartIdx);
 					}
					
 				}
				
 				ptr = this.root;
				
 			}
			
 		}
		
 	}

	// "catdog" ca at td
	// cat dog
	// ca td og

	// TODO: should return list, should be insert a string
	public void constructDataStructureWithPartition(ArrayList<String> dataSet, short maxLengthStr) {
		
		this.stringsIndexTable = Utility.generateHashmap(dataSet);
		Node ptr = this.root;

		for (int i = 0; i < dataSet.size(); i++) {

			// System.out.println("String " + i);

			String currData = dataSet.get(i);

			short startIdxPtr = 0;
			short lastIdxPtr = maxLengthStr;
			short currIdx = 0;
	
			while (currIdx < currData.length()) {
				
				if (startIdxPtr == lastIdxPtr) {	
					lastIdxPtr += maxLengthStr;
					if (lastIdxPtr > currData.length()) {
						lastIdxPtr = (short) currData.length();
					}
			   }

				char currChar = currData.charAt(currIdx);

				if (ptr.isLeaf() || !ptr.checkChild(currChar)) {
					Node newChild = new Node();
					ptr.addChild(currChar, newChild);
				}
				ptr = ptr.getChild(currChar);

				if (startIdxPtr != 0 && (lastIdxPtr != currData.length() || currIdx != currData.length() - 1)) {
					ptr.addIndexPointerBetweenWildcard(i, startIdxPtr);
				}
				if (startIdxPtr == 0) {
					ptr.addIndexPointerBeforeWildcard(i, startIdxPtr);
				}
				if (lastIdxPtr == currData.length()) {
					ptr.addIndexPointerAfterWildcard(i, startIdxPtr);
				}
				if (startIdxPtr == 0 && lastIdxPtr ==  currData.length() && currIdx == currData.length() - 1) {
					ptr.addIndexPointerNoWildcard(i, startIdxPtr);
				}

				if (currIdx == lastIdxPtr - 1) {
					startIdxPtr++;
					currIdx = startIdxPtr;
					ptr = this.root;
				}
				else {
					currIdx++;
				}

			}

		}

	}

	public void constructDataStructure2Grams (List<String> dataSet) {

		this.stringsIndexTable = Utility.generateHashmap(dataSet);
		Node ptr = this.root;
		short startIdxPtr = 0;
		short currIdx = 0;
		short charTakenCount = 0;

		for (int i = 0; i < dataSet.size(); i++) {

			System.out.println("String i: " + i);

			String currData = dataSet.get(i);

			ptr = this.root;
			startIdxPtr = 0;
			currIdx = 0;
			charTakenCount = 0;

			while (currIdx < currData.length()) {

				// Retrieve the current character in that string from the corresponding index
				char currChar = currData.charAt(currIdx);

				// If ptr has no child for that current character, create a new child node
				if (ptr.isLeaf() || !ptr.checkChild(currChar)) {
					Node newChild = new Node();
					ptr.addChild(currChar, newChild);
				}
				// Go to that existing / new child node
				ptr = ptr.getChild(currChar);
				charTakenCount++;

				// Add the corresponding suffix index pointer to the current node 
				if (startIdxPtr != 0 && currIdx != currData.length() - 1) { // Between wildcard (*a*)
					ptr.addIndexPointerBetweenWildcard(i, startIdxPtr);
				}
				if (startIdxPtr == 0) { // before wildcard (a*)
					ptr.addIndexPointerBeforeWildcard(i, startIdxPtr);
				}
				if (currIdx == currData.length() - 1) { // after wildcard (*a)
					ptr.addIndexPointerAfterWildcard(i, startIdxPtr);
				}
				if (startIdxPtr == 0 && currIdx == currData.length() - 1) { // no wildcard
					ptr.addIndexPointerNoWildcard(i, startIdxPtr);
				}

				// Reprompt the index variables
				if (charTakenCount == this.FIXED_N_GRAMS_VALUE) {
					charTakenCount = 0;
					startIdxPtr += this.FIXED_N_GRAMS_VALUE - 1;
					currIdx = startIdxPtr;
					ptr = this.root;
				}
				else {
					currIdx++;
				}

			}

		}

	}

	public void insertStringIntoDataStructure (String str) {

		// Find the key for the string
		this.incCurrKey();

		System.out.println("String: " + this.getCurrKey());

		// Insert the string into the string index table
		this.insertStringIntoIndexTable(str, this.getCurrKey());

		// Initialize the variable pointers
		Node ptr = this.root;
		short startIdxPtr = 0;
		short currIdx = 0;
		short charTakenCount = 0;

		while (currIdx < str.length()) {

			// Retrieve the current character in that string from the corresponding index
			char currChar = str.charAt(currIdx);

			// If ptr has no child for that current character, create a new child node
			if (ptr.isLeaf() || !ptr.checkChild(currChar)) {
				Node newChild = new Node();
				ptr.addChild(currChar, newChild);
			}
			// Go to that existing / new child node
			ptr = ptr.getChild(currChar);
			charTakenCount++;

			// Add the corresponding suffix index pointer to the current node 
			if (startIdxPtr != 0 && currIdx != str.length() - 1) { // Between wildcard (*a*)
				ptr.addIndexPointerBetweenWildcard(this.getCurrKey(), startIdxPtr);
			}
			if (startIdxPtr == 0) { // before wildcard (a*)
				ptr.addIndexPointerBeforeWildcard(this.getCurrKey(), startIdxPtr);
			}
			if (currIdx == str.length() - 1) { // after wildcard (*a)
				ptr.addIndexPointerAfterWildcard(this.getCurrKey(), startIdxPtr);
			}
			if (startIdxPtr == 0 && currIdx == str.length() - 1) { // no wildcard
				ptr.addIndexPointerNoWildcard(this.getCurrKey(), startIdxPtr);
			}

			// Reprompt the index variables
			if (charTakenCount == this.FIXED_N_GRAMS_VALUE) {
				charTakenCount = 0;
				startIdxPtr += this.FIXED_N_GRAMS_VALUE - 1;
				currIdx = startIdxPtr;
				ptr = this.root;
			}
			else {
				currIdx++;
			}

		}

	}

	public void insertStringIntoIndexTable (String str, int key) {


		if (this.stringsIndexTable == null) {
			this.stringsIndexTable = new HashMap<>();
		}

		this.stringsIndexTable.put(key, str);

	}

	// public int findNextKeyInIndexTable () {

	// 	this.incLastKey();
	// 	return this.getLastKey();
	
	// }

	public void insertStringsToDataStructureFromTxtFile (String filename) {

		// Initalize the file
		File file = new File(filename);
		try (Scanner sc = new Scanner(file)) {

			while (sc.hasNextLine()) {
				this.insertStringIntoDataStructure(sc.nextLine());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<String> searchDataStructure2Grams (String wildcard) {

		// Initialize the variables
		boolean leftWC = false;
		boolean rightWC = false;
		short wcCountFound = 0;
		short charFound = 0;
		short startIdxPtr = 0;
		short currIdx = 0;
		short nextIdxPtr = 1;
		boolean continuous = false;

		Node ptr = this.root;
		Record record = new Record();
		List<String> result = new ArrayList<String>();

		// Split the wildcard
		List<String> wildcardSuffixes = Utility.splitWildcards(wildcard);
		String currWildcardSuff;

		for (int i = 0; i < wildcardSuffixes.size(); i++) {

			currWildcardSuff = wildcardSuffixes.get(i);
			System.out.println("currWildcardSuff: " + currWildcardSuff);

			// Reset the variables
			leftWC = false;
			rightWC = false;
			// wcCountFound = 0;
			charFound = 0;
			startIdxPtr = 0;
			currIdx = 0;
			nextIdxPtr = 1;
			continuous = false;

			ptr = this.root;

			while (currIdx < currWildcardSuff.length()) {

				// PRINT SEARCH CONFIGURATIONS
				System.out.println("BEFORE");
				System.out.println("================================================");
				System.out.println("currChar: " + wildcard.charAt(currIdx));
				System.out.println("leftWC: " + leftWC);
				System.out.println("rightWC: " + rightWC);
				System.out.println("wcCountFound: " + wcCountFound);
				System.out.println("charFound: " + charFound);
				System.out.println("startIdxPtr: " + startIdxPtr);
				System.out.println("currIdx: " + currIdx);
				System.out.println("nextIdxPtr: " + nextIdxPtr);
				System.out.println("continuous: " + continuous);
				System.out.println("================================================");
				
				if (currWildcardSuff.charAt(currIdx) == '*') {
					leftWC = true;
					currIdx++;
					nextIdxPtr++;
				}

				else {

					if (ptr.isLeaf() || !ptr.checkChild(currWildcardSuff.charAt(currIdx))) {
						return null;
					}

					ptr = ptr.getChild(currWildcardSuff.charAt(currIdx));
					charFound++;

					if (currIdx == currWildcardSuff.length() - 1) {
						recordWildcardSuffix(record, ptr, leftWC, rightWC, wcCountFound, charFound, continuous);
						wcCountFound++;
						break;
					}

					else if (currWildcardSuff.charAt(nextIdxPtr) == '*') {
						rightWC = true;
						recordWildcardSuffix(record, ptr, leftWC, rightWC, wcCountFound, charFound, continuous);
						wcCountFound++;
						break;
					}

					else if (charFound == this.FIXED_N_GRAMS_VALUE && currWildcardSuff.charAt(nextIdxPtr) != '*') {
						rightWC = true;
						continuous = true;
						recordWildcardSuffix(record, ptr, leftWC, rightWC, wcCountFound, charFound, continuous);
						leftWC = true;
						rightWC = false;
						charFound = 0;
						wcCountFound++;
						// startIdxPtr = currIdx;
						// currIdx = startIdxPtr;
						// nextIdxPtr = (short) (currIdx + 1);
						ptr = this.root;
					}

					else {
						currIdx++;
						nextIdxPtr++; 
					}

				}
				// System.out.println("AFTER");
				// System.out.println("================================================");
				// System.out.println("currChar: " + wildcard.charAt(currIdx));
				// System.out.println("leftWC: " + leftWC);
				// System.out.println("rightWC: " + rightWC);
				// System.out.println("wcCountFound: " + wcCountFound);
				// System.out.println("charFound: " + charFound);
				// System.out.println("startIdxPtr: " + startIdxPtr);
				// System.out.println("currIdx: " + currIdx);
				// System.out.println("nextIdxPtr: " + nextIdxPtr);
				// System.out.println("continuous: " + continuous);
				// System.out.println("================================================");

			}

		}

		System.out.println("AFTER");
		System.out.println("================================================");
		System.out.println("currChar: " + wildcard.charAt(currIdx));
		System.out.println("leftWC: " + leftWC);
		System.out.println("rightWC: " + rightWC);
		System.out.println("wcCountFound: " + wcCountFound);
		System.out.println("charFound: " + charFound);
		System.out.println("startIdxPtr: " + startIdxPtr);
		System.out.println("currIdx: " + currIdx);
		System.out.println("nextIdxPtr: " + nextIdxPtr);
		System.out.println("continuous: " + continuous);
		System.out.println("================================================");

		// Retrieve the strings
		Set<Integer> keySet = record.getKeySet();
		List<Integer> keys = new ArrayList<Integer>(keySet);

		for (int i = 0; i < keys.size(); i++) {
			if (record.getFoundCountAtIndex(keys.get(i)) == wcCountFound) {
				result.add(this.stringsIndexTable.get(keys.get(i)));
			}
		}
		
		return result;

	}

	public void recordWildcardSuffix (Record record, Node ptr, boolean leftWC, boolean rightWC, short wcCountFound, short charFound, boolean continuous) {
		
		NodePointers currNode;

		if (leftWC && rightWC) {
			currNode = ptr.retrieveArrayPointerBetweenWildcard();
		}
		else if (leftWC) {
			currNode = ptr.retrieveArrayPointerAfterWildcard();
		}
		else if (rightWC) {
			currNode = ptr.retrieveArrayPointerBeforeWildcard();
		}
		else {
			currNode = ptr.retrieveArrayPointerNoWildcard();
		}

		if (currNode != null) {

			// System.out.println("test");

			Iterator it = currNode.getArrayPointersIterator();
			NodePointersValue val;

			if (wcCountFound == 0) {

				while (it.hasNext()) {
					val = (NodePointersValue) it.next();
					record.setCharLocAtIndex(val.getIndex(), (short) (val.getCharLocsAtIndex(0) + (charFound - 1)));
					record.incFoundCountAtIndex(val.getIndex());
				}

			}
			else {
				
				while(it.hasNext()) {
					val = (NodePointersValue) it.next();
					
					if (continuous && record.hasIndex(val.getIndex()) && val.findCharLoc((short) (record.getCharLocAtIndex(val.getIndex()))) && record.getFoundCountAtIndex(val.getIndex()) == wcCountFound) {

						record.setCharLocAtIndex(val.getIndex(), (short) (record.getCharLocAtIndex(val.getIndex()) + (charFound - 1)));
						record.incFoundCountAtIndex(val.getIndex());

					}
					else if (!continuous && record.hasIndex(val.getIndex())) {

						short nextCharLoc = val.findMinimumCharLoc(record.getCharLocAtIndex(val.getIndex()));
						if (nextCharLoc != -1) {
							record.setCharLocAtIndex(val.getIndex(), (short) (nextCharLoc + (charFound - 1)));
							record.incFoundCountAtIndex(val.getIndex());
						}

					}

				}

			}

		}

	}

	// public List<String> searchDataStructure2Grams (String wildcard) {
		
	// 	// Initialize the initial variables
	// 	List<String> result = new ArrayList<String>();
	// 	Record record = new Record();
	// 	Node ptr = this.root;

	// 	short startIdxPtr = 0;
	// 	short currIdx = 0;
	// 	short charTakenCount = 0;
	// 	boolean contSuffix = false;
	// 	short foundWildcardSuffixCount = 0;
		
	// 	// Generate the wildcard suffixes
	// 	List<String> wildcardSuffixes = Utility.splitWildcards(wildcard);

	// 	// Iterate through the wildcard suffixes
	// 	for (int i = 0; i < wildcardSuffixes.size(); i++) {

	// 		String currWildcardSuffix = wildcardSuffixes.get(i);

	// 		// Iterate through the wildcard suffix in a 2 gram way
	// 		startIdxPtr = 0;
	// 		currIdx = 0;
	// 		charTakenCount = 0;
	// 		contSuffix = this.countWildcardSuffixLength(currWildcardSuffix) > this.FIXED_N_GRAMS_VALUE;
	// 		foundWildcardSuffixCount = 0;

			// while (currIdx < currWildcardSuffix.length()) {
				
			// 	// Retrieve the current character in that suffix from the corresponding index
			// 	char currChar = currWildcardSuffix.charAt(currIdx);

			// 	if (currChar != '*') {

			// 		// Traverse through the wildcard suffix for each 2 gram suffix (2 characters)
			// 		// If don't found the current character, exit early
			// 		if (!ptr.isLeaf() && !ptr.checkChild(currChar)) {
			// 			return null;
			// 		}
			// 		// Traverse through the node
			// 		else {
			// 			ptr = ptr.getChild(currChar);
			// 			charTakenCount++;
			// 		}

			// 		// Reprompt the index variables
			// 		if (charTakenCount == this.FIXED_N_GRAMS_VALUE) {
			// 			foundWildcardSuffixCount++;
			// 			if (contSuffix) {
		// 					// Record cont suffix

		// 					ptr = this.root;
		// 				}
		// 				charTakenCount = 0;
		// 				startIdxPtr += this.FIXED_N_GRAMS_VALUE - 1;
		// 				currIdx = startIdxPtr;
		// 			}
		// 			else {
		// 				currIdx++;
		// 				startIdxPtr++;
		// 			}

		// 		}
		// 		else {
		// 			currIdx++;
		// 			startIdxPtr++;
		// 		}

		// 	}

		// 	// Record the suffix
		// 	if (!contSuffix) {
		// 		recordSuffixNotCont(record, ptr, currWildcardSuffix);
		// 	}

		// }
		
		// // Retrive the filtered key from the record (who has the found count value that is same as the total found count in the wildcard)
		// Short foundCountWildcard = this.findFoundCountWildcard(wildcard);
		// ArrayList<Integer> KeyList = record.filterKeyWithTotalFoundCount(foundCountWildcard);

		// // Get all the string from the string index table and the corresponding filtered key
		// for(int i = 0; i < KeyList.size(); i++) {
		// 	int currKey = KeyList.get(i);
	// 		result.add(this.stringsIndexTable.get(currKey));
	// 	}

	// 	// Return the result
	// 	return result;

	// }

	// private int countWildcardSuffixLength (String wcSuffix) {
		
	// 	if (wcSuffix.charAt(0) == '*' && wcSuffix.charAt(wcSuffix.length() - 1) == '*') {
	// 		return wcSuffix.length() - 2;
	// 	}
	// 	else if (wcSuffix.charAt(0) == '*' || wcSuffix.charAt(wcSuffix.length() - 1) == '*') {
	// 		return wcSuffix.length() - 1;
	// 	}
	// 	else {
	// 		return wcSuffix.length();
	// 	}

	// }

	// public void recordSuffixNotCont (Record record, Node ptr, String suffix) {

	// 	// Initialize the Node Pointer variable
	// 	NodePointers currNodePtr;

	// 	// Initialize the Node Pointer variable with the corresponding suffix and Node pointer
	// 	if (suffix.charAt(0) == '*' && suffix.charAt(suffix.length() - 1) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerBetweenWildcard();
	// 	}
	// 	else if (suffix.charAt(0) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerAfterWildcard();
	// 	}
	// 	else if (suffix.charAt(suffix.length() - 1) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerBeforeWildcard();
	// 	}
	// 	else {
	// 		currNodePtr = ptr.retrieveArrayPointerNoWildcard();
	// 	}

	// 	// Retrieve the array pointer iterator for the current Node Pointer
	// 	if (currNodePtr != null) {
	// 		Iterator<NodePointersValue> arrayPtrIter = currNodePtr.getArrayPointersIterator();

	// 		// Iterate through all the NodePointersValue in the linked list
	// 		NodePointersValue currVal;
	// 		while (arrayPtrIter.hasNext()) {
	
	// 			currVal = arrayPtrIter.next();
	
	// 			// Record the key index into the record table
	// 			record.setCharLocAtIndex(currVal.getIndex(), currVal.getCharLocsAtIndex(0));
	// 			record.incFoundCountAtIndex(currVal.getIndex());
	
	// 		}
	// 	}

	// }

	public void incCurrKey () {
		this.currKey++;
	}

	public int getCurrKey () {
		return this.currKey;
    }

	

	// public void recordSuffixCont (Record record, Node ptr, String suffix, short foundWildcardSuffixCount, short currIdx) {
		
	// 	// Initialize the Node Pointer variable
	// 	NodePointers currNodePtr;

	// 	// Initialize the Node Pointer variable with the corresponding suffix and Node pointer
	// 	if (foundWildcardSuffixCount == 1) {
	// 		if (suffix.charAt(0) == '*') {
	// 			currNodePtr = ptr.retrieveArrayPointerBetweenWildcard();
	// 		}
	// 		else {
	// 			currNodePtr = ptr.retrieveArrayPointerBeforeWildcard();
	// 		}
	// 	}
	// 	else {
	// 		if (suffix.charAt())
	// 	}


	// 	if (suffix.charAt(0) == '*' && suffix.charAt(suffix.length() - 1) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerBetweenWildcard();
	// 	}
	// 	else if (suffix.charAt(0) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerAfterWildcard();
	// 	}
	// 	else if (suffix.charAt(suffix.length() - 1) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerBeforeWildcard();
	// 	}
	// 	else {
	// 		currNodePtr = ptr.retrieveArrayPointerNoWildcard();
	// 	}

	// 	// Retrieve the array pointer iterator for the current Node Pointer
	// 	if (currNodePtr != null) {
	// 		Iterator<NodePointersValue> arrayPtrIter = currNodePtr.getArrayPointersIterator();

	// 		// Iterate through all the NodePointersValue in the linked list
	// 		NodePointersValue currVal;
	// 		while (arrayPtrIter.hasNext()) {
	
	// 			currVal = arrayPtrIter.next();
	
	// 			// Record the key index into the record table
	// 			record.setCharLocAtIndex(currVal.getIndex(), currVal.getCharLocsAtIndex(0));
	// 			record.incFoundCountAtIndex(currVal.getIndex());
	
	// 		}
	// 	}

	// }

	// public short findFoundCountWildcard(String wildcard) {

	// 	short foundCountWildcard = 0;
	// 	List<String> wildcardSuffixes = Utility.splitWildcards(wildcard);
	// 	for (int i = 0; i < wildcardSuffixes.size(); i++) {
	// 		if (this.countWildcardSuffixLength(wildcardSuffixes.get(i)) < this.FIXED_N_GRAMS_VALUE) {
	// 			foundCountWildcard += 1;
	// 		}
	// 		else {
	// 			foundCountWildcard += this.countWildcardSuffixLength(wildcardSuffixes.get(i)) - (this.FIXED_N_GRAMS_VALUE - 1);
	// 		}
	// 	}

	// 	return foundCountWildcard;

	// }

	// public ArrayList<String> searchDataStructure(String wildcard) {
		
	// 	ArrayList<String> results = new ArrayList<>();
	// 	ArrayList<String> wildcardSuffixes = Utility.splitWildcards(wildcard);
	// 	Record record = new Record();
		
	// 	for (int i = 0; i < wildcardSuffixes.size(); i++) {
			
	// 		String currSuffix = wildcardSuffixes.get(i);
						
	// 		// Traverse through the wildcard suffix in the tree
	// 		Node ptr = traverseSuffix(currSuffix);
			
	// 		if (ptr == null) {
	// 			return results;
	// 		}
			
	// 		// Record the indexes into the record
	// 		recordSuffix(record, ptr, currSuffix, i);
			
	// 		ptr = this.root;
			
	// 	}
		
	// 	// Find the indexes in the record table that founds all the wildcard suffixes
	// 	Set<Integer> recordKeySet = record.getKeySet();
	// 	ArrayList<Integer> recordKeys = new ArrayList<Integer>(recordKeySet);
		
	// 	for (int i = 0; i < recordKeys.size(); i++) {
			
	// 		int currKey = recordKeys.get(i);
			
	// 		if (record.getFoundCountAtIndex(currKey) == wildcardSuffixes.size()) {
	// 			results.add(this.stringsIndexTable.get(currKey));
	// 		}
			
	// 	}
		
	// 	return results;
		
	// }

	// private ArrayList<KeySuffix> generateSuffixes (ArrayList<String> strings) {
		
	// 	ArrayList<KeySuffix> suffixes = new ArrayList<>();
		
	// 	for (int i = 0; i < strings.size(); i++) {
			
	// 		String currStr = strings.get(i);
			
	// 		for (int j = 0; j < currStr.length(); j++) {
				
	// 			KeySuffix newKeySuffix = new KeySuffix(i, j, currStr.substring(j));
	// 			suffixes.add(newKeySuffix);
				
	// 		}
			
	// 	}
		
	// 	return suffixes;
			
	// }
	
	// 	private Node traverseSuffix(String suffix) {
	// 	Node ptr = this.root;
		
	// 	// Traverse through the wildcard suffix in the tree
	// 	for (int i = 0; i < suffix.length(); i++) {
			
	// 		char currChar = suffix.charAt(i);
			
	// 		if (currChar != '*') {
				
	// 			if (ptr.checkChild(currChar)) {
	// 				ptr = ptr.getChild(currChar);
	// 			}
	// 			else {
	// 				return null;
	// 			}
	
	// 		}
			
	// 	}
		
	// 	return ptr;
	// }
	
	// private void recordSuffix(Record record, Node ptr, String suffix, int iteration) {
		
	// 	NodePointers currNodePtr;
		
	// 	if (suffix.charAt(0) == '*' && suffix.charAt(suffix.length() - 1) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerBetweenWildcard();
	// 	}
	// 	else if (suffix.charAt(0) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerAfterWildcard();
	// 	}
	// 	else if (suffix.charAt(suffix.length() - 1) == '*') {
	// 		currNodePtr = ptr.retrieveArrayPointerBeforeWildcard();
	// 	}
	// 	else {
	// 		currNodePtr = ptr.retrieveArrayPointerNoWildcard();
	// 	}
		
	// 	Set<Integer> keySet = currNodePtr.getKeySet();
	// 	ArrayList<Integer> keys = new ArrayList<Integer>(keySet); 
		
	// 	for (int i = 0; i < keys.size(); i++) {
			
	// 		int currKey = keys.get(i);
			
	// 		if (iteration == 0) {
	// 			record.setCharLocAtIndex(currKey, currNodePtr.retrieveCharLocsAtIndex(currKey).get(0));
	// 			record.incFoundCountAtIndex(currKey);
	// 		}
			
	// 		else if (record.hasIndex(currKey) && iteration == record.getFoundCountAtIndex(currKey)){
				
	// 			int nextMinIdx = Utility.findMinimum(currNodePtr.retrieveCharLocsAtIndex(currKey), record.getCharLocAtIndex(currKey));
				
	// 			if (nextMinIdx != -1) {
	// 				record.setCharLocAtIndex(currKey, nextMinIdx);
	// 				record.incFoundCountAtIndex(currKey);
	// 			}
				
	// 		}
			
	// 	}
		
	// }
	
	class KeySuffix {
		
		private int key;
		private int startIdx;
		private String suffix;
		
		public KeySuffix (int key, int startIdx, String suffix) {
			this.key = key;
			this.startIdx = startIdx;
			this.suffix = suffix;
		}
		
		public int getKey () {
			return this.key;
		}
		
		public int getStartIdx () {
			return this.startIdx;
		}
		
		public String getSuffix () {
			return this.suffix;
		}
		
	}

}







