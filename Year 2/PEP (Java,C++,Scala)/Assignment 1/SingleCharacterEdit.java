public class SingleCharacterEdit {

    public static String singleCharacterEditWillTurnAIntoB(String a, String b) {
    	
    		int difference = a.length() - b.length();
		
		String missingLetter = null;
		int index = 0;
		
		String[] shortWord = a.split("");
		String longWord = b;
		
		if (b.length() < a.length()) {
			shortWord = b.split("");
			longWord = a;
		}

		for (int i=0; i<shortWord.length; i++) {
			longWord = longWord.replaceFirst(shortWord[i],"");
		}
		
		if (longWord.length() == 1) {
			missingLetter = longWord;
		}else {
			return null;
		}
		
		for (int j=0; j < shortWord.length; j++) {
			if (a.charAt(j) != b.charAt(j)) {
				index = j;
				break;
			}
			if (j == shortWord.length-1) {
				index = shortWord.length;
			}
		}
				
		if (difference == 1) {
			return "remove," + index;
		}
		else if(difference == 0) {
			return "replace," + index + "," + missingLetter;
		}else if(difference == -1) {
			return "insert," + index + "," + missingLetter;
		}else {
			return null;
		}
    }
}
