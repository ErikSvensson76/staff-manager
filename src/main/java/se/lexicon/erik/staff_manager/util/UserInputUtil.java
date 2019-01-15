package se.lexicon.erik.staff_manager.util;

import java.util.Scanner;

public class UserInputUtil {
	
	private static final Scanner scanner = new Scanner(System.in);
	
	public static String getString() {
		return scanner.nextLine();
	}

}
