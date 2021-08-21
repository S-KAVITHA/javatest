package Phase1Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class DigitalLockerMain {

	private static Scanner myobj;
	private static String fileSeparator1 = System.getProperty("file.separator");

	public static void main(String[] args) {

		// String path = System.getProperty("user.dir");
		initapplicatin();
		welcomeScreen();
		displaymain();
	}

	public static void initapplicatin() {
		myobj = new Scanner(System.in);

	}

	public static void welcomeScreen() {
		// step 1. collect input from user console
		String userName = "Kavitha S";
		System.out.println("---------------------------------------------------------------");
		System.out.println("         :: Welcome to Digital Locker System :: ");
		System.out.println("                 Developed by " + userName);
		System.out.println("---------------------------------------------------------------");

		System.out.println("\nYou can use this application to ,  ");
		System.out.println("\n1 -> Registration. ");
		System.out.println("2 -> Login ");
		System.out.println("3 -> Exit the application");

	}

	public static void displaymain() {

		boolean loopagain = true;
		do {

			try {

				System.out.println("\nEnter choice [1-3]: ");
				Scanner input1 = new Scanner(System.in);
				int choice = input1.nextInt();

				switch (choice) {
				case 1:

					adminfileCreation();
					break;

				case 2:

					LoginAsAdmin();
					break;

				case 3:

					System.out.println("Terminated");
					loopagain = false;
					input1.close();
					System.exit(0);
					break;

				default:
					System.out.println("Please enter a valid input");
					break;
				}
			} catch (Exception e) {
				System.out.println("Please enter a valid input");
			}

		} while (loopagain);

	}

	public static void adminfileCreation() {

		// Scanner myobj = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Add new file with user details:  ");
		System.out.println("--------------------------------------------");

		System.out.println("Enter an user name : ");
		String userName = myobj.nextLine();

		System.out.println("Enter an password : ");
		String password = myobj.nextLine();

		System.out.println("Enter an email ID : ");
		String emailID = myobj.nextLine();

		System.out.println("Enter an role : ");
		String role = myobj.nextLine();

		UserCredential userdetails = new UserCredential(userName, password, emailID, role);

		File createFolder = new File("main");

		// If file doesn't exist, create the main folder
		if (!createFolder.exists()) {
			createFolder.mkdirs();
		}

		writeAdminfile(userdetails);
		// }

	}

	private static void writeAdminfile(UserCredential userdetails) {

		try {
			String pathDir = System.getProperty("user.dir") + fileSeparator1 + "main";
			String fileNamePath = pathDir + fileSeparator1 + "adminlogin" + ".txt";

			File tempFile = new File(fileNamePath);
			System.out.println(fileNamePath);
			System.out.println(tempFile);

			boolean append = tempFile.exists();
			System.out.println(append);
			
			if (append) {
				FileOutputStream file = new FileOutputStream(fileNamePath, append);
				ObjectOutputStream out = new ObjectOutputStream(file);

				out.writeObject(userdetails);
					
				out.close();
				file.close();
				System.out.println("File Updation Sucessfull !!!");
				
			} else {

				FileOutputStream file = new FileOutputStream(fileNamePath);
			
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(userdetails);
				
			out.close();
			file.close();
			System.out.println("File Creation Sucessfull !!!");
			}
			

		} catch (Exception ex) {
			System.out.println("File Creation Un-Sucessfull !");
		}
	}

	public static void LoginAsAdmin() {
		// TODO Auto-generated method stub

	}

}
