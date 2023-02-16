package wildcard_challenge;

import java.util.*;

import Testing.Testing;
import SuffixTreeStrategy.SuffixTreeStrategy;

public class WildcardChallengeTesting {
	
	public static final String JSON_FILENAME = "sample_data_big.json";	
	public static final int SET_LENGTH = 1000000;
	public static final int STRING_LENGTH = 200;

	// public static final short PARTITION_LIMIT = 3;

	public static void main (String [] args) {
		
		// Output Benchmark Title
		System.out.println();
		System.out.println("========================================");
		System.out.println("BENCHMARK STATISTICS");
		System.out.println("========================================");
		System.out.println();

		// 1. Benchmark Construct Data Structure - Big Size
        System.out.println("1. Construct Data Structure - Big Size");
        System.out.println("========================================");
		System.out.println("CONFIGURATION");
		System.out.println("Set Length: " + SET_LENGTH);
		System.out.println("String Length: " + STRING_LENGTH);
		// System.out.println("Partition Limit: " + PARTITION_LIMIT);
		System.out.println("========================================");

		// Read the Data Set
		System.out.println("Reading the data set...");
		ArrayList<String> data = Testing.readDataSetFromJSONFile(JSON_FILENAME);
		System.out.println("Reading done!");
		// Take the data set heap memory space
		long dataSetHeapMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		// Construct the data structure
		SuffixTreeStrategy suffixTreeStrategy = new SuffixTreeStrategy();
		System.out.println("Constructing data structure...");
		// Start the construction time
		long startConstructTime = System.nanoTime();
		// suffixTreeStrategy.constructDataStructureWithPartition(data, PARTITION_LIMIT);
		suffixTreeStrategy.constructDataStructure2Grams(data);
		// Finish the costruction time
		long endConstructTime = System.nanoTime();
		System.out.println("Construction done!");

		// Calculate the heap memory usage for the data structure (total memory - free memory - data set memory)
		long dataStructureHeapMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - dataSetHeapMemory;
		// Calculate the construction time (end - start)
		double elapsedTimeInSeconds = (double) (endConstructTime - startConstructTime) / 1_000_000_000;

		// Print the results
		System.out.println("========================================");
		System.out.println("RESULTS");
		System.out.println("Estimated data structure heap memory: " + dataStructureHeapMemory + " bytes");
		System.out.println("Estimated heap memory per string: " + (dataStructureHeapMemory / SET_LENGTH) + " bytes/string");
		System.out.println("Estimated construction time: " + elapsedTimeInSeconds + " seconds");
		System.out.println("Estimated construction time per string: " + (elapsedTimeInSeconds / SET_LENGTH) + " seconds/string");
		System.out.println();

		// System.out.println();
		
	}

}
