import java.util.Scanner;
import java.util.Random;

public class NumberGuess{
	public static void main(String[] args){

		Scanner user_input = new Scanner(System.in);
		Random random = new Random();

		int done = 0;
		int ui;
		int guesses = 0;
		int uinumber = 0;
		System.out.println("type \"0\" if you want the default number, or choose a number of your choice! (Less than 2147483647 please)");
		while (done == 0) {
			uinumber = Integer.parseInt(user_input.next());
			if (uinumber > 2147483647){
				System.out.println("That number is too big!");
			}
			
			if (uinumber < 0) {
				System.out.println("Do not put in negative values!");
			} else if (uinumber <= 2147483647) {
				done = 1;
			}
		}
		if(uinumber == 0){
			uinumber = 100;
		}
		int randomnumber = random.nextInt(uinumber);
		done = 0;
		System.out.println();
		System.out.println("Guess a number between 0 and " + uinumber + "!");
		
		while(done == 0){
			ui = Integer.parseInt(user_input.next());
			if (ui == -42) {
				System.out.println(randomnumber);
			}
			if (ui > 2147483647) {
				System.out.println("That number is too big!");
			} else {
				if (ui > randomnumber){
					System.out.println("Your guess was too high. Guess again!");
					guesses++;
				}
				if(ui < randomnumber){
					System.out.println("Your guess was too low. Guess again!");
					guesses++;
				}
				if (ui == randomnumber) {
					done = 1;
					System.out.println("You Guessed it!");
					System.out.println("The number was " + randomnumber + "!");
					System.out.println("It took you " + guesses + " guesses ato get the right number!");
				}
			}
		}

		System.out.println();
	}
}