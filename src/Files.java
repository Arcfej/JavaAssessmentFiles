import java.util.Scanner;

/**
 * 
 */

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
	public Files() {

	}

	/**
	 * @param args
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
	public void runFilesTest(Scanner in) {
		System.out.println("You asked for the following file: " + askForFile(in));
	}
	
	/**
	 * Ask a filename from the user.
	 * 
	 * @param in the scanner through which the user type in their input.
	 * @return the filename which the user asked for.
	 */
	public String askForFile(Scanner in) {
		System.out.println("Which text file would you like to open?");
		return in.next();
	}
}
