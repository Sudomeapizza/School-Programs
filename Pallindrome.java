import java.util.Scanner;
//import a scanner

public class Pallindrome {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("Your phrase: ");
		String line = scan.nextLine();
		String before1 = " ", before2 = ",", after = "";
		System.out.println();
		String linewithoutspace = line.replaceAll(",","");
		linewithoutspace = linewithoutspace.replaceAll("\\s","");
		String lineresult = "";
		char letter = ' ';
		for (int i = linewithoutspace.length() - 1; i >= 0; i--) {
			letter = linewithoutspace.charAt(i);
			lineresult = lineresult + Character.toString(letter);
		}
		if (lineresult.equals(linewithoutspace)) {
			System.out.println(line + " is a PALLINDROME!");
		} else {
			System.out.println(line + " is not a PALLINDROME!");
		}
		System.out.println();
	}
}