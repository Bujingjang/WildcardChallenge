package Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utility {

	public static HashMap<Integer, String> generateHashmap (List<String> strings) {
		
		HashMap<Integer, String> indexTable = new HashMap<>();
		
		for (int i = 0; i < strings.size(); i++) {
			indexTable.put(i, strings.get(i));
		}
		
		return indexTable;
		
	}
	
	public static List<String> splitWildcards(String wildcard) {
		
		List<String> wildcardSuffixes = new ArrayList<>();
		String record = "";
		
		// Iterate through the wildcard characters
		for (int i = 0; i < wildcard.length(); i++) {
			
			// If the index is the last character
			if (i == wildcard.length() - 1) {
				record += wildcard.charAt(i);
				wildcardSuffixes.add(record);
			}
			
			// If the index is the first character
			else if (i == 0) {
				record += wildcard.charAt(i);
			}

			// If the index is in the middle
			else {
				
				if (wildcard.charAt(i) == '*') {
					record += wildcard.charAt(i);
					wildcardSuffixes.add(record);
					record = "*";
				}
				
				else {
					record += wildcard.charAt(i);
				}
				
			}
			
		}
		
		return wildcardSuffixes;
		
	}
	
	public static int findMinimum (ArrayList<Short> ints, short baseNum) {
		
		for (int i = 0; i < ints.size(); i++) {
			if (baseNum < ints.get(i)) {
				return ints.get(i);
			}
		}
		
		return -1;
	}

	public static short binarySearch (ArrayList<Short> arr, short target, short left, short right) {

		if (right >= left) {
            short mid = (short) (left + (right - left) / 2);
 
            if (arr.get(mid) == target)
                return mid;

            if (arr.get(mid) > target)
                return binarySearch(arr, target, left, (short) (mid - 1));
 
            return binarySearch(arr, target, (short) (mid + 1), right);
        }
 
        return -1;

	}
	
}




















