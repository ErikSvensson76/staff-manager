package se.lexicon.erik.staff_manager.util;

public class NullChecker {
	
	public static boolean nullCheck(Object... objects) {
		for(Object obj: objects) {
			if(obj == null) {
				return true;
			}
		}
		return false;
	}

}
