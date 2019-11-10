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
		files.runFilesTest(in);
		// Close system input after the end of running
		in.close();
	}

	/**
	 * Test how the Files class works.
	 */
    private void runFilesTest(Scanner in) {
//	    // Read files
//		String text = askForFile(in);
//		if (!text.isEmpty()) {
//			System.out.println("The content of the file:");
//			System.out.println(text);
//		}

//        // Write to files
//        writeToFile(in);

		// Copy files
		copyFile(in);
	}
	
	/**
	 * Ask a filename from the user and return the content of that file if it exists.
	 * 
	 * @param in the scanner through which the user type in their input.
	 * @return the content of the file the user asked for.
	 */
	public String askForFile(Scanner in) {
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
			// TODO swap if and try order
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				System.out.println("Error closing file: " + e.getMessage());
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
}
