package Phase1Project;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class digitalLocker {

	public static void main(String[] args) {

		boolean loopagain = true;

		// step 1. collect input from user console

		String userName = "Kavitha S";
		@SuppressWarnings("resource")
		Scanner input1 = new Scanner(System.in);

		// String path = System.getProperty("user.dir");

		System.out.println("---------------------------------------------------------------");
		System.out.println("         :: Welcome to Digital Locker System :: ");
		System.out.println("                 Developed by " + userName);
		System.out.println("---------------------------------------------------------------");

		System.out.println("\nYou can use this application to ,  ");
		System.out.println("\n1 -> Create files for each user with login credentials. ");
		System.out.println("2 -> Retrieving the file names in an ascending order. ");
		System.out.println("3 -> Perform File operations such as add, delete , search. ");
		System.out.println("4 -> Exit the application");

		do {

			//try {
				System.out.println("\nEnter choice [1-4]: ");
				int choice = input1.nextInt();

				switch (choice) {
				case 1:

					userfileCreation.filecreate();
					break;

				case 2:

					String currentDir = System.getProperty("user.dir");
					File fileDir = new File(currentDir);

					File[] files = fileDir.listFiles();
					List<String> listFile = new ArrayList<String>();

					for (File file : files) {
						if ((file.isDirectory() == false) && (file.getAbsolutePath().endsWith(".txt"))) {
							listFile.add(file.getAbsolutePath());
						}
					}

					System.out.println("----------------------------------------");
					System.out.println("::Sorting filenames in ascending order::");
					System.out.println("----------------------------------------");

					Collections.sort(listFile);
					listFile.forEach(System.out::println);

					System.out.println("-----------------------------------------");
					System.out.println("::Sorting filenames in descending order::");
					System.out.println("-----------------------------------------");

					Collections.sort(listFile, Collections.reverseOrder());

					listFile.forEach(System.out::println);

					break;

				case 3:

					filleOperations.dofileoperations();
					break;

				case 4:

					System.out.println("Terminated");
					loopagain = false;
					input1.close();
					System.exit(0);
					break;

				default:
					System.out.println("Please enter a valid input");
					break;
				}
			

			//catch (Exception e) {
				//System.out.println(e.getClass().getName());
			}

		} while (loopagain);

	}
}
