package assetProgram;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void csvWrangle(int numShares) {
		// Variable for the buffer to read into
		String myLine;
		try {
			BufferedReader myBuff = new BufferedReader(new FileReader(
					"GOOGprices.csv"));
			int counter;
			counter = 0;
			while ((myLine = myBuff.readLine()) != null) {
				if (counter == 0) {
					// Do nothing with first line read
				}
				// don't do anything the first iteration then take 0 and 6th
				// element of the split up String array
				else {
					String[] stringSplit = myLine.split(",");
					float adjValue = Float.parseFloat(stringSplit[6]);
					float totalValue = numShares * adjValue;

					System.out.printf("%-30s  %-30d %-30.2f\n", stringSplit[0],
							numShares, totalValue);
				}
				counter++;
			}
			myBuff.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} finally {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		// Set up scanner
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Welcome to the Asset Management System");
		System.out.print("Input the number GOOG shares: ");
		// Get the number from the user
		int userInt = myScanner.nextInt();
		System.out.println("Your GOOG assets:\n");
		System.out.printf("%-30s  %-30s %-30s\n", "Date", "Number of Shares",
				"Total Value");
		csvWrangle(userInt);
	}

}
