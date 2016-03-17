// This program finds all the prime numbers in the specified range.

import java.util.Scanner;

public class SieveV2{
	public static void main(String[] args){ 	// This is the starting code
		Scanner scan = new Scanner(System.in);
		System.out.print("How high do you want the program to search?: ");
		int userinput = Integer.parseInt(scan.next());
		boolean[] booleanarray = new boolean[userinput];
		for (int b = 0; b < userinput; b++){
			booleanarray[b] = true;
		}
		booleanarray[0] = false;
		//This is the start of finding the prime numbers
		for (int i = 1; i < userinput; i++){
			if (booleanarray[i - 1] == true) {
				for (int b = i + i; b <= userinput; b += i) {
					booleanarray[b - 1] = false;
				}
			}
		}
		// This is the end of finding the prime numbers
		System.out.println("Your prime numbers are:");
		for(int i = 1; i < userinput; i++){
			if(booleanarray[i] == true){
				// Prints out the prime numbers
				System.out.println("· " + (i + 1));
			}
		}
	}
}