package wildcard_challenge;

import SuffixTreeStrategy.SuffixTreeStrategy;

public class WildcardChallengeBenchmark {
    
    public static final String TXT_FILENAME = "wiki_list_big.txt";

    public static void main (String [] args) {
    
        // Output Benchmark Title
		System.out.println();
		System.out.println("========================================");
		System.out.println("BENCHMARK STATISTICS");
		System.out.println("========================================");
		System.out.println();

        // 1.Benchmark Construct Data Structure
        System.out.println("1. Construct Data Structure - Big Size");
        System.out.println("========================================");
		System.out.println("CONFIGURATION");
        System.out.println("File Name: " + TXT_FILENAME);
		System.out.println("========================================");

        // Initialize and Construct the Data Structure
        SuffixTreeStrategy suffixTreeStrategy = new SuffixTreeStrategy();
		System.out.println("Constructing data structure...");
        // Start the construction time
        long startConstructTime = System.nanoTime();
        suffixTreeStrategy.insertStringsToDataStructureFromTxtFile(TXT_FILENAME);
        // Finish the costruction time
		long endConstructTime = System.nanoTime();
		System.out.println("Construction done!");

        // Calculate the heap memory usage for the data structure (total memory - free memory)
        long dataStructureHeapMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		// Calculate the construction time (end - start)
		double elapsedTimeInSeconds = (double) (endConstructTime - startConstructTime) / 1_000_000_000;

        // Print the results
        System.out.println("========================================");
		System.out.println("RESULTS");
        System.out.println("Estimated data structure heap memory: " + dataStructureHeapMemory + " bytes");
		System.out.println("Estimated construction time: " + elapsedTimeInSeconds + " seconds");

    }

}