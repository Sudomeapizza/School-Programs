import java.util.Scanner;

public class Seive{
	
	public static void main(String[] args){

		Scanner scan = new Scanner(System.in);
		System.out.print("How high do you want the program to search?: ");
		int userinput = Integer.parseInt(scan.next());

		boolean[] booleanarray = new boolean[userinput];
		int[] numberarray = new int[userinput];

		for (int b = 0; b < userinput; b++){
			numberarray[b] = b + 1;
			booleanarray[b] = false;
		}

		booleanarray[0] = true;

		for (int i = 1; i < userinput + 1; i++){

			for (int a = 2; a < i; a++){
				if (i % a == 0){
					booleanarray[i -1] = true;
				}
			}

			if (booleanarray[i - 1] == false){
				System.out.println(i);
			}
		}
	}
}