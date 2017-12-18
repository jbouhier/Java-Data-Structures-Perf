package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
		int increment = 20000;

		// The number of steps to run.
		int numSteps = 20;
		
		// THe number of characters to start with.
		int start = 50000;

		// Key
		System.out.println("NumberOfChars\tBasicTime\tEfficientTime");

		// numToCheck = chars to read w/ BasicDocument & EfficientDocument
		for (int numToCheck = start; numToCheck < numSteps * increment + start;
			 numToCheck += increment)
		{
			System.out.print(numToCheck + "\t");
			String strToCheck = getStringFromFile(textfile, numToCheck);

			// BasicDocument bench
			long basicStartTime = System.nanoTime();

			for (int i = 0; i < trials; i++) {
				BasicDocument basicDoc = new BasicDocument(strToCheck);
				double fleschScore = basicDoc.getFleschScore();
			}

			long basicEndTime = System.nanoTime();
			double basicDuration = (basicEndTime - basicStartTime) / 1_000_000_000.0; // Seconds
			String formatedBasicDur = String.format("%01f", basicDuration);
			System.out.print(formatedBasicDur + "\t");


			// EfficientDocument bench
			long EfficientStartTime = System.nanoTime();

			for (int i = 0; i < trials; i++) {
				EfficientDocument effiiDoc = new EfficientDocument(strToCheck);
				double fleschScore = effiiDoc.getFleschScore();
			}

			long EfficientEndTime = System.nanoTime();
			double efficientDuration = (EfficientEndTime - EfficientStartTime) / 1_000_000_000.0; // Seconds
			String formatedEfficientDur = String.format("%01f", efficientDuration);
			System.out.println(formatedEfficientDur);
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile = new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
