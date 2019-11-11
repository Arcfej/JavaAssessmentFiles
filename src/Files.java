import java.io.*;
import java.util.Scanner;

/**
 * This class handles the file readings and writings of a program.
 * 
 * @author MiklosMayer
 *
 */
public class Files {

	/**
	 * Constants used by the decipherFile() method.
	 */
	private static final String crypt1 = "cipherabdfgjk";
	private static final String crypt2 = "lmnoqstuvwxyz";

	/**
	 * Basic constructor.
	 */
    private Files() {

	}

	/**
     * Entry point of the application
     *
	 * @param args Starter arguments for running the program
	 */
	public static void main(String[] args) {
		Files files = new Files();
		Scanner in = new Scanner(System.in);
		files.run(in);
		// Close system input after the end of running
		in.close();
	}

	/**
	 * Display options to the user and execute program functions according to their commands.
	 */
    private void run(Scanner in) {
    	boolean run = true;
		while (run) {
			System.out.println("Chose a function: ");
			System.out.println("1) Read a file");
			System.out.println("2) Write to a file");
			System.out.println("3) Copy a file");
			System.out.println("4) Decipher mystery.txt");
			System.out.println("5) Calculate the average scores of the competition's candidates");
			System.out.println("0) Exit the program");
			int command;
			try {
				command = Integer.parseInt(in.next());
			} catch (NumberFormatException e) {
				command = -1;
			}
			switch (command) {
				case 1:
					// Read files
					String fileName = askForFile(in);
					if (!fileName.isEmpty()) {
						System.out.println("The content of the file:");
						System.out.println(fileName);
					}
					break;
				case 2:
					// Write to files
					writeToFile(in);
					break;
				case 3:
					// Copy files
					copyFile(in);
					break;
				case 4:
					// Decipher file
					decipherFile();
					break;
				case 5:
					// Calculate competition's average scores
					processingScores();
					break;
				case 0:
					// Exit the program
					run = false;
					break;
				default:
					System.out.println("Not a valid command!\n");
			}
		}
	}
	
	/**
	 * Ask a filename from the user and return the content of that file if it exists.
	 * 
	 * @param in the scanner through which the user type in their input.
	 * @return the content of the file the user asked for.
	 */
	private String askForFile(Scanner in) {
		System.out.println("Which text file would you like to open?");
		// Load the file with the given filename and return it.
		return loadTextFile(in.next());
	}
	
	/**
	 * Load a text file from the disk.
	 * 
	 * @param fileName The name of the file to be loaded.
	 * @return the content of that file in a String.
	 */
    private String loadTextFile(String fileName) {
		String file = "";
		BufferedReader bufferedReader = null;
		
		// Try to read every line of the file
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			while (bufferedReader.ready()) {
				file = file.concat(bufferedReader.readLine()).concat("\n");
			}
		}
		
		// Catch errors
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error during reading the file: " + e.getMessage());
			file = null;
		}
		
		// Close reader
		finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					System.out.println("Error closing file: " + e.getMessage());
				}
			}
		}
		
		return file;
	}

	/**
	 * Ask for lines from the user and save them in a txt file.
	 *
	 * @param in the input stream through the user type in their texts.
	 */
    private void writeToFile(Scanner in) {
		final String USER_INPUT_FILE_PATH = "input.txt";

        // Try open or create a new file for writing
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(USER_INPUT_FILE_PATH))) {
            // Prompt the user to write line by line until they enter an empty line
            System.out.println("Please input the lines of the file. In case of an empty line the file will be saved.");
            while (true) {
                String line = in.nextLine();
                if (line.isEmpty()) break;
                writer.println(line);
            }
        } catch (FileNotFoundException | SecurityException e) {
            System.out.println("Access denied: " + e.getMessage());
        }
	}

	/**
	 * Get two filenames from the user and copy one of them into the other.
	 *
	 * @param in the input stream the user type in the filenames.
	 */
	private void copyFile(Scanner in) {
    	BufferedReader from = null;
    	PrintWriter to = null;
		try {
			// Get the filenames from the user.
			System.out.println("Which file do you like to copy?");
			from = new BufferedReader(new FileReader(in.next()));
			System.out.println("What should be the filename of the new copy?");
			to = new PrintWriter(in.next());

			// Read every lines from the original file and display them to the user
			// while writing them to the new file.
			while (from.ready()) {
				String line = from.readLine();
				System.out.println(line);
				to.println(line);
			}
		}

		// Catch the errors
		catch (FileNotFoundException e) {
			System.out.println("File cannot be opened, read or written to: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error during reading the file: " + e.getMessage());
		}

		// Close the files
		finally {
			if (from != null) {
				try {
					from.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (to != null) to.close();
		}
	}

	/**
	 * Decipher the mystery.txt file into deciphered.txt.
	 */
	private void decipherFile() {
		BufferedReader mystery = null;
		PrintWriter deciphered = null;

		try {
			mystery = new BufferedReader(new FileReader("mystery.txt"));
			deciphered = new PrintWriter("deciphered.txt");
			// Decipher and print the file line by line
			while (mystery.ready()) {
				String newLine = mystery.readLine();
				String decipheredLine = cipherDecipherString(newLine);
				System.out.println(decipheredLine);
				deciphered.println(decipheredLine);
			}
		}

		// Catch the errors
		catch (FileNotFoundException e) {
			System.out.println("File cannot be opened, read or written to: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error during reading the file: " + e.getMessage());
		}

		// Close the files.
		finally {
			if (mystery != null) {
				try {
					mystery.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (deciphered != null) {
				deciphered.close();
			}
		}
	}

	/**
	 * Method to encipher and decipher a given String using parallel arrays (crypt1 & crypt2)
	 *
	 * @param text A String containing text that is to be enciphered or deciphered
	 * @return A new String containing the result, e.g. the en/deciphered version of the String provided as an input
	 */
	private static String cipherDecipherString(String text)
	{
		// declare variables we need
		int i, j;
		boolean found = false;
		StringBuilder temp = new StringBuilder(); // empty String to hold converted text

		for (i = 0; i < text.length(); i++) // look at every character in text
		{
			found = false;
			if ((j = crypt1.indexOf(text.charAt(i))) > -1) // is char in crypt1?
			{
				found = true; // yes!
				temp.append(crypt2.charAt(j)); // add the cipher character to temp
			}
			else if ((j = crypt2.indexOf(text.charAt(i))) > -1) // and so on
			{
				found = true;
				temp.append(crypt1.charAt(j));
			}

			if (! found) // to deal with cases where char is NOT in crypt2 or 2
			{
				temp.append(text.charAt(i)); // just copy across the character
			}
		}
		return temp.toString();
	}

	/**
	 * Read the scores of the competition candidates from details.txt, calculates their average scores and
	 * write them to the averages.txt file.
	 */
	private void processingScores() {
		BufferedReader scores = null;
		PrintWriter averages = null;

		try {
			// Open the files
			scores = new BufferedReader(new FileReader("details.txt"));
			averages = new PrintWriter("averages.txt");

			// Parse all the candidates' scores, calculate the average, then display and save the results
			while (scores.ready()) {
				String[] candidate = scores.readLine().split(" ");
				String firstName = candidate[0];
				String lastName = candidate[1];
				int total = 0;
				for (int i = 2; i < candidate.length; i++) {
					total += Integer.parseInt(candidate[i]);
				}
				float average = (float) total / (candidate.length - 2);
				// Example: Bush, Mildred: Average score is x.xx.
				String out = String.format("%1s, %2s: Average score is %3.2f.", lastName, firstName, average);
				// Write to screen and output file
				System.out.println(out);
				averages.println(out);
			}
		}

		// Catch the errors
		catch (FileNotFoundException e) {
			System.out.println("File is not exist or cannot be opened: " + e.getMessage());
		} catch (SecurityException e) {
			System.out.println("Access denied: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error while file reading: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error with parsing integer: " + e.getMessage());
		}

		// Close the files
		finally {
			if (scores != null) {
				try {
					scores.close();
				} catch (IOException e) {
					System.out.println("Error while closing file: " + e.getMessage());
				}
			}
			if (averages != null) {
				averages.close();
			}
		}
	}
}
