package wildcard_challenge;

import Testing.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class WildcardDataSetApplication {

    private static final String JSON_FILENAME = WildcardChallengeTesting.JSON_FILENAME;
    private static final int SET_LENGTH = WildcardChallengeTesting.SET_LENGTH;
    private static final int STRING_LENGTH = WildcardChallengeTesting.STRING_LENGTH;

    public static void main (String [] args) {

        // Output Generate Configuration
        System.out.println();
        System.out.println("================================");
        System.out.println("GENERATE CONFIGURATION");
        System.out.println("================================");
        System.out.println("Output file: " + JSON_FILENAME);
        System.out.println("Set Length: " + SET_LENGTH);
        System.out.println("String Length: " + STRING_LENGTH);
        System.out.println("================================");

        // Generating the random set strings into a JSON file
        Testing.generateDataSetIntoJSONFile(JSON_FILENAME, SET_LENGTH, STRING_LENGTH);

        // Output Reading Configuration
        // System.out.println();
        // System.out.println("================================");
        // System.out.println("READ CONFIGURATION");
        // System.out.println("================================");
        // System.out.println("Input file: " + JSON_FILENAME);
        // System.out.println("================================");

        // Reading from JSON File and store into ArrayList variable
        // ArrayList<String> dataSet = Testing.readDataSetFromJSONFile(JSON_FILENAME);

        // Print the data set
        // for (int i = 0; i < dataSet.size(); i++) {
        //     System.out.println(dataSet.get(i));
        // }

        // Print the variable (object) size
        // System.out.println("Data Set Size: " + printObjectSize(dataSet));

        System.out.println();

    }
}
