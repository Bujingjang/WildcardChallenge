package wildcard_challenge;

import java.util.ArrayList;

import Utility.Utility;

public class LogicTesting {
    
    public static void main(String[] args) {

        String testStr = "FidelioT";

        short nGram = 2;

        // short startIdx = 0;
        // short endIdx = nGram;
        // short currIdx = 0;

        // System.out.println("===========================");
        // while (currIdx < testStr.length()) {

            // Reprompt idx variables
            // if (currIdx == endIdx && startIdx == endIdx) {
            //     endIdx += nGram;
            //     if (endIdx > testStr.length()) {
            //         endIdx = (short) testStr.length();
            //     }
            // }
            
        //     // Logic
        //     System.out.println("currChar: " + testStr.charAt(currIdx));
        //     System.out.println("currIdx: " + currIdx);
        //     System.out.println("startIdx: " + startIdx);
        //     System.out.println("endIdx: " + endIdx);
        //     System.out.println("===========================");

        //     // Reprompt idx variables
        //     if (currIdx == endIdx - 1) {
        //         startIdx++;

        //         currIdx = startIdx;
        //     }
        //     else {
        //         currIdx++;
        //     }
            
        // }

        // short startIdx = 0;
        // short currIdx = 0;
        // short charTakenCount = 0;

        // System.out.println("===========================");
        // while (currIdx < testStr.length()) {

        //     // Logic
        //     System.out.println("currChar: " + testStr.charAt(currIdx));
        //     charTakenCount++;
        //     System.out.println("currIdx: " + currIdx);
        //     System.out.println("charTakenCount: " + charTakenCount);
        //     System.out.println("===========================");

        //     // Reprompt the index variables
        //     if (charTakenCount == nGram) {
        //         charTakenCount = 0;
        //         startIdx += nGram - 1;
        //         currIdx = startIdx;
        //     }
        //     else {
        //         currIdx++;
        //     }

        // }

        ArrayList<Short> test = new ArrayList<Short>();
        // test.add((short) 0);
        // test.add((short) 4);
        // test.add((short) 5);
        // test.add((short) 6);
        // test.add((short) 9);

        System.out.println(Utility.binarySearch(test, (short) 0, (short) 0, (short) (test.size() - 1)));
    }

}
