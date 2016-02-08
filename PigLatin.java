import java.util.Scanner;

public class PigLatin {
	public static void main(String[] args) {

		Scanner Scan = new Scanner(System.in);
		boolean over = false;
		boolean repeatuntil = false;
		int lettercounterforindividualwords = 0;
		String individualword = "";
		char space = ' ';
		char character = ' ';

		System.out.println();
		String input = Scan.nextLine();
		System.out.println();

		for (int i = 0; i < input.length(); i++){
			character = input.charAt(i);
			System.out.print(character + "   ");
			System.out.println(vowelchecker( Character.toString(character)));
		}

		while (over == false) {

			while (repeatuntil == false) {
				if (input.charAt(lettercounterforindividualwords) == space || input.charAt(lettercounterforindividualwords) >= input.length()){
					individualword = input.substring(0,lettercounterforindividualwords);
					repeatuntil = true;
					System.out.print(individualword);
				}
				lettercounterforindividualwords++;
			}

			character = individualword.charAt(0);

			if (vowelchecker(Character.toString(character)) == true) {

				System.out.print("");
				saystring("yay");
				over = true;
			} else {
				//add
			}

			if (lettercounterforindividualwords >= input.length()) {
				over = true;
			}
		}
	}

	public static void saystring (String message) {
		System.out.print(message);
	}
	public static void saychar (char message) {
		System.out.print(message);
	}


	public static Boolean vowelchecker (String inputA) {
		try {
		    Thread.sleep(100);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		if (inputA.equalsIgnoreCase("a")){
			saystring("vowel!");
			return true;
		} else if (inputA.equalsIgnoreCase("e")){
			saystring("vowel!");
			return true;
		} else if (inputA.equalsIgnoreCase("i")){
			saystring("vowel!");
			return true;
		} else if (inputA.equalsIgnoreCase("o")){
			saystring("vowel!");
			return true;
		} else if (inputA.equalsIgnoreCase("u")){
			saystring("vowel!");
			return true;
		} else if (inputA.equalsIgnoreCase(" ")){
			saystring("space!");
			return false;
		} else if (inputA.equalsIgnoreCase("!")){
			saystring("punctuation!");
			return false;
		} else if (inputA.equalsIgnoreCase("?")){
			saystring("punctuation!");
			return false;
		} else if (inputA.equalsIgnoreCase(".")){
			saystring("punctuation!");
			return false;
		} else {
			saystring("not a vowel!");
			return false;
		}
	}
}