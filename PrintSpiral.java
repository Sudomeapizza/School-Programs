import java.util.Scanner;

public class PrintSpiral {
	
	public static void main(String[] args){

		Scanner scan = new Scanner(System.in);
		System.out.print("here:");
		int input = Integer.parseInt(scan.next());
		boolean end = false;
		int[][] chart = new int[input][input];
		int col = 0;
		int row = 0;
		int i = 1;

		System.out.println(chart.length);
		System.out.println(chart[0].length);
		System.out.println(col + " " + row + " " + i);

		int x = input;
		// x equals how many steps it takes before it changes direction

		while (i < input * input) {

			System.out.println("(" + x + ")");
			for (int b = 0; b < x; b++){		// This for loop goes right
				System.out.print("·" + row);
				chart[row][col] = i;
				row++;
				i++;
			}
				x--;

			System.out.println();

			row--;
			col++;



			if (i != input * input) {

				System.out.println("(" + x + ")");
				for (int b = 0; b < x; b++){		// This for loop goes down
					System.out.print("··" + col);
					chart[row][col] = i;
					col++;
					i++;
				}

				System.out.println();

				col--;
				row--;

				System.out.println("(" + x + ")");
				for (int b = 0; b < x; b++){		// This for loop goes left
					System.out.print("···" + row);
					chart[row][col] = i;
					row--;
					i++;
				}

					x--;

				System.out.println();

				row++;
				col--;

				System.out.println("(" + x + ")");
				for (int b = 0; b < x; b++){		// This for loop goes up
					System.out.print("····" + col);
					chart[row][col] = i;
					col--;
					i++;
				}

				row++;
				col++;

				pause(100);

				System.out.println();
				System.out.println("|" + i + "|");
			}
			if (input % 2 ==1){
				chart[row][col] = input * input;
			}
		}
		report(chart,input);
	}

	public static void pause(int la){
		try {
		    Thread.sleep(la);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}

	public static void report(int[][] result, int input) {
		for (int col = 0; col < input; col++) {
			for (int row = 0; row < input; row++){
				say(result[row][col]);
			}
			System.out.println();
		}
	}

	public static void say(int line) {
		int linelength = String.valueOf(line).length();
		if (linelength == 1){
			System.out.print(line + "      ");
		} else if (linelength == 2){
			System.out.print(line + "     ");
		} else if (linelength == 3){
			System.out.print(line + "    ");
		} else if (linelength == 4){
			System.out.print(line + "   ");
		} else if (linelength == 5){
			System.out.print(line + "  ");
		} else if (linelength == 6){
			System.out.print(line + " ");
		} else {
			System.out.print(line);
		}
	}
}