import java.util.Scanner;
import java.util.Random;

public class collatz{
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";   // all of these make preset colors
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";
	
	// The Function

	public static long NextCollatzStep(long num, int color, int times, long ui, String underline) {
		color = (int)(Math.random() * 7 + 32);
		String col = "\u001B[" + color + "m";
		System.out.print("[# " + times + "] ");
		System.out.print(col);
		System.out.println(num + ANSI_RESET);
		times++;
		try {
		    Thread.sleep(50);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		if (num > 1) {
			if (num % 2 == 0) {
				long a = num / 2;
				NextCollatzStep(a,color,times,ui,underline);
			} else {
				long b = (num * 3) + 1;
				NextCollatzStep(b,color,times,ui,underline);
			}
		} else {
			System.out.println();
			System.out.println("[System] Done");

			int randomintegerattheendofalldatcode = times - 1;
			
			System.out.println("[System] Took " + underline + randomintegerattheendofalldatcode + ANSI_RESET + " steps!");
		}
	return num;
	}

	// The main code

	public static void main(String[] args) {
	
		Random rand = new Random();

		Scanner user_input = new Scanner(System.in);

		long a;
		long b;
		long ui;
		// The longest number is: 9223372036854775807;

		// All the variable announced here

		int n = rand.nextInt(50) + 1;
		int color = 30;
		int times = 1;

		String colors;
		String underline = "\u001B[4m";
		String blink = "\u001B[5m";

		// Asking for input

		System.out.println();
		System.out.println(ANSI_GREEN + "Put a NUMBER below!" + ANSI_RESET);
		System.out.println("This is the biggest Integer you can put in :" + ANSI_CYAN + "2147483647" + ANSI_RESET);
		System.out.println("Your input:");
		ui = Integer.parseInt(user_input.next());
		System.out.println();


		System.out.println(blink + "========Start========" + ANSI_RESET); // START

		NextCollatzStep(ui,color,times,ui,underline); // Takes in outside variables

		System.out.println(blink + "=========END=========" + ANSI_RESET); // END
	}
}