package wildcard_challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Utility.Utility;
import Record.Record;
import Node.*;
import SuffixTreeStrategy.SuffixTreeStrategy;

public class WildcardChallengeApplication {

	public static void main(String[] args) {
		
		System.out.println("Wildcard Challenge");
		System.out.println("------------------");
		
//		String wildcard = "acb*";
//		ArrayList<String> wildcardSuffixes = Utility.splitWildcards(wildcard);
//		
//		for (int i = 0; i < wildcardSuffixes.size(); i++) {
//			System.out.println(wildcardSuffixes.get(i));
//		}
		
//		Record record = new Record();
//		
//		record.insertIndex(2, 3);
//		System.out.println(record.getCharLocAtIndex(2));
//		record.setCharLocAtIndex(2, 4);
//		System.out.println(record.getCharLocAtIndex(2));
		
//		SuffixTreeStrategy suffixTreeStrategy = new SuffixTreeStrategy();
//		ArrayList<String> test = new ArrayList<>();
//		test.add("abc");
//		test.add("cba");
//		test.add("abcd");
//		ArrayList<String> suffixes = suffixTreeStrategy.generateSuffixes(test);
//		for (int i = 0; i < suffixes.size(); i++) {
//			System.out.println(suffixes.get(i));
//		}
		
//		Node node = new Node();
//		Node newNode = new Node();
//		
//		Node ptr = node;
//		
//		ptr.addChild('a', newNode);
//		
//		System.out.println(node.getChild('a'));
		
		// Test on set of strings ["abc", "bca", "aac"]
		// ArrayList<String> strings = new ArrayList<>();
		// strings.add("abcd");
		// strings.add("badc");
		// strings.add("bcad");
		
		// short maxLengthStr = 3;

		SuffixTreeStrategy suffixTreeStrategy = new SuffixTreeStrategy();
		
		// suffixTreeStrategy.constructDataStructureWithoutPartition(strings);
		// suffixTreeStrategy.constructDataStructureWithPartition(strings, maxLengthStr);
		// suffixTreeStrategy.constructDataStructure2Grams(strings);
		suffixTreeStrategy.insertStringIntoDataStructure("abcd");
		suffixTreeStrategy.insertStringIntoDataStructure("badc");
		suffixTreeStrategy.insertStringIntoDataStructure("bcad");
		
		System.out.println("construction done");
		
		String wildcard = "";
		
		List<String> results = suffixTreeStrategy.searchDataStructure2Grams(wildcard);
		
		if (results != null) {
			System.out.println(results.size());
			for (int i = 0; i < results.size(); i++) {
				System.out.println(results.get(i));
			}
		}
		
		System.out.println("Searching done");

		// Node root = suffixTreeStrategy.getNode();
		
//		// Test children - done
		// System.out.println(root.getChild('b'));
//		
//		// Test NodePointers
		// Node child = root.getChild('c');
		// NodePointers arrPtr = child.retrieveArrayPointerAfterWildcard();
		// System.out.println(arrPtr);
		// Iterator itr = arrPtr.getArrayPointers().iterator();

		// while (itr.hasNext()) {
		// 	NodePointersValue curr = (NodePointersValue) itr.next();
		// 	System.out.println(curr.getIndex());
		// 	System.out.println(curr.getCharLocs());
		// }

		// for (int i = 0; i < afterWildcardA.retrieveCharLocsAtIndex(2).size(); i++) {
		// 	System.out.println(afterWildcardA.retrieveCharLocsAtIndex(2).get(i));
		// }

		// UTILITY TESTING
		// System.out.println(Utility.splitWildcards("*abc*def"));
		
	}

}













