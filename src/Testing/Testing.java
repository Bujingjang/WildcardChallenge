package Testing;

import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Testing {
	

	public static ArrayList<String> generateSetOfStrings (int setLength, int stringLength) {
		
		 String characters = "abcdefghijklmnopqrstuvwxyz"
		 						+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		 						+ "1234567890"
		 						+ "`-=[];'/.,~_+{}|:?><";
		
//		String characters = "a";
		
		ArrayList<String> dataSet = new ArrayList<>();
		
		Random rand = new Random();
		
		for (int i = 0; i < setLength; i++) {
			
			String temp = "";
			
			for (int j = 0; j < stringLength; j++) {
				temp += characters.charAt(rand.nextInt(characters.length()));
			}
			
			dataSet.add(temp);
			
		}
		
		return dataSet;
		
	}
	
	public static void writeDataSet (String filename, ArrayList<String> dataSet) {
		
		String outputFilename = filename + ".txt";
		File fout = new File("Data.txt");
	    try (FileOutputStream fos = new FileOutputStream(outputFilename); BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));) {
	        
	    	for (int i = 0; i < dataSet.size(); i++) {
	    		bw.write(dataSet.get(i));
	    		bw.newLine();
	    	}
	    	
	    } catch (IOException ignored) {
	    	
	    }
		
	}
	
	public static ArrayList<String> readDataSet (String filename) {
		
		String inputFilename = filename + ".txt";
		
		Scanner sc;
		try {
			sc = new Scanner(new File(inputFilename));
			
			ArrayList<String> lines = new ArrayList<>();
			while (sc.hasNextLine()) {
			  lines.add(sc.nextLine());
			}
			
			return lines;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public static void generateDataSetIntoJSONFile (String filename, int setLength, int stringLength) {

		JSONArray dataSet = new JSONArray();

		String characters = "abcdefghijklmnopqrstuvwxyz"
		+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		+ "1234567890"
		+ "`-=[];'/.,~_+{}|:?><";

		//		String characters = "a";

		Random rand = new Random();

		for (int i = 0; i < setLength; i++) {

			String temp = "";

			for (int j = 0; j < stringLength; j++) {
				temp += characters.charAt(rand.nextInt(characters.length()));
			}

			dataSet.add(temp);

		}

		try {
			FileWriter file = new FileWriter(filename);
			file.write(dataSet.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("JSON file created: "+ filename);

	}

	public static ArrayList<String> readDataSetFromJSONFile (String filename) {
		
		JSONParser jsonParser = new JSONParser();
		ArrayList<String> dataSet = new ArrayList<String>();
		
		try (FileReader reader = new FileReader(filename))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONArray retrievedDataSet = (JSONArray) obj;
			
			//Iterate over string array
			retrievedDataSet.forEach( str ->  dataSet.add((String) str));

			return dataSet;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}

}
