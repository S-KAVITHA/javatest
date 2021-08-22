package PhaseOneProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class lockerOperations {

	private static Scanner keyboard = new Scanner(System.in);
	private static UserCredentials userCredentials = new UserCredentials();
	private static String fileSep = System.getProperty("file.separator");
	static String pathDirect;
	static boolean loopagain1 = true;

	public static void dolockerOperations(String inpUsername) {

		pathDirect = System.getProperty("user.dir") + fileSep + inpUsername;

		System.out.println("1 -> Store User Credentials ");
		System.out.println("2 -> List User Files ");
		System.out.println("4 -> Show User Credentials");
		System.out.println("5 -> Delete User Files ");
		System.out.println("5 -> Return to Previous Menu");
		System.out.println("6 -> Exit the application");

		do {
			System.out.println("\nEnter choice [1-4]: ");

			int getchoice = keyboard.nextInt();
			switch (getchoice) {
			case 1:

				storeCredentials(inpUsername);
				break;

			case 2:
				listallfilenames(inpUsername);

			case 3:

				fetchCredentials();
				break;

			case 4:

				deleteCredentials();
				break;

			case 5:
				System.out.println("Return to Main Menu");
				return;

			case 6:
				System.out.println("Exit the application");
				loopagain1 = false;
				// input.close();
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid input");
				break;

			}
		} while (loopagain1);
	}

	private static void listallfilenames(String listUsername) {
	
		String listfilenames = pathDirect + fileSep ;
			
		File fileDirList = new File(listfilenames);
				
		File[] listOffiles = fileDirList.listFiles();
		List<String> listFile = new ArrayList<String>();
		
		
		for (File gettfile : listOffiles) {
			if ((gettfile.isDirectory() == false) && (gettfile.getAbsolutePath().endsWith(".txt"))) {
				listFile.add(gettfile.getAbsolutePath());
				
			}
		}
		
		System.out.println("----------------------------------------");
		System.out.println("::Sorting filenames in ascending order::");
		System.out.println("----------------------------------------");

		Collections.sort(listFile);
		listFile.forEach(System.out::println);

	}

	public static void storeCredentials(String loggedInUser) {

		System.out.println("==========================================");
		// System.out.println("* *");
		System.out.println("*   Welcome to Digital Locker System	 *");
		System.out.println("*    Store Your Credentials here !!!	 *");
		// System.out.println("* *");
		System.out.println("==========================================");
		System.out.println(loggedInUser);

		userCredentials.setLoggedInUser(loggedInUser);

		System.out.println("Enter Site Name :");
		String siteName = keyboard.next();
		userCredentials.setSiteName(siteName);

		System.out.println("Enter Username :");
		String siteusername = keyboard.next();
		userCredentials.setUsername(siteusername);

		System.out.println("Enter Password :");
		String password = keyboard.next();
		userCredentials.setPassword(password);

		File createFolder = new File(loggedInUser);

		// If file doesn't exist, create the main folder
		if (!createFolder.exists()) {
			createFolder.mkdirs();
		}

		String Framefilename = pathDirect + fileSep + siteusername + ".txt";

		// File tempFile = new File(Framefilename);

		try {
			// create file output stream.
			FileOutputStream sitefile = new FileOutputStream(Framefilename);

			// create object stream
			ObjectOutputStream siteout = new ObjectOutputStream(sitefile);

			// method to serialize object
			siteout.writeObject(userCredentials);

			siteout.close();
			sitefile.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Your Credentials Stored Sucessfully !!!");

	}

	// fetch credentials
	public static void fetchCredentials() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   Welcome to Digital Locker System *");
		System.out.println("*      Your Credentials are here 	 *");
		System.out.println("*					*");
		System.out.println("==========================================");

		System.out.println("Enter an website user name : ");
		String getwebuser = keyboard.next();

		try {
			// 1. read a file
			String fetchfilename = pathDirect + fileSep + getwebuser + ".txt";

			FileInputStream filef = new FileInputStream(fetchfilename);

			// 2. create a input stream object

			ObjectInputStream inf = new ObjectInputStream(filef);

			// 3. method to de-serialized object
			UserCredentials listOfCreds = (UserCredentials) inf.readObject();

			// while (fetchis.available() != 0) {

			// }
			// 3. method to de-serialized object
			// UserCredentials employee = (UserCredentials) fetchin.readObject();

			System.out.println("Website User Credentials !");
			System.out.println("****************************");
			System.out.println("Website Name : " + listOfCreds.getSiteName());
			System.out.println("User Name    : " + listOfCreds.getUsername());
			System.out.println("Password     : " + listOfCreds.getPassword());

			inf.close();
			filef.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void deleteCredentials() {
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   Welcome to Digital Locker System	 *");
		System.out.println("*      Remove Your Credentials 	 *");
		System.out.println("*					*");
		System.out.println("==========================================");

		System.out.println("Enter an website user name to remove: ");
		String delwebuser = keyboard.next();

		// 1. read a file
		String delfilename = pathDirect + fileSep + delwebuser + ".txt";
		File delfile = new File(delfilename);
        
		if (delfile.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}

	}

}
