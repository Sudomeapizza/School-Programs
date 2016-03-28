import java.util.Scanner;

public class PascalSquare003V1{
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.print("How big do you want your PascalSquare? ");
		int input = Integer.parseInt(scan.next());
		long[][] chart = new long[input][input];

		for (int row = 0; row < input; row++){
			for (int col = 0; col < input; col++){
				if (row == 0 || col == 0){
					chart[row][col] = 1;
				} else {
					chart[row][col] = (chart[row - 1][col]) + (chart[row][col - 1]);
					/* Adds the adjacent numbers */
				}
			}
		}
		sayPS(input,chart);
	}

	public static void sayPS(int size, long[][] array){
		for (int row = 0; row < size; row++){
			for (int col = 0; col < size; col++){
				System.out.print(array[row][col] + " ");
			}
			System.out.println();
		}
	}
}