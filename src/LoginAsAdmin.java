package Phase1Project;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class LoginAsAdmin {

	static String fileSeparator1 = System.getProperty("file.separator");
	private static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {

		checkloginInfo();

	}

	public static void checkloginInfo() {

		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO LOGIN PAGE	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		System.out.println("Enter Username :");
		String inpUsername = keyboard.next();
		System.out.println("Enter Password :");
		String inpPassword = keyboard.next();

		boolean found = false;

		fetchFileInfo(inpUsername, inpPassword);

	}

	public static void fetchFileInfo(String inpUsername, String inpPassword) {
		try {

			String pathDir1 = System.getProperty("user.dir") + fileSeparator1 + "main";
			String fileNamePath1 = pathDir1 + fileSeparator1 + "adminlogin" + ".txt";

			// 1. read a file
			FileInputStream adminfile = new FileInputStream(fileNamePath1);

			// 2. create a input stream object
			ObjectInputStream in = new ObjectInputStream(adminfile);

			// 3. method to de-serialized object
			UserCredential users = (UserCredential) in.readObject();

			String userId = users.getUserName();
			String userpassword = users.getPassword();
			System.out.println("Login bef eSuccessful ! 200OK");

			if (userId.equals(inpUsername) && userpassword.equals(inpPassword)) {

				System.out.println("Login Successful ! 200OK");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
