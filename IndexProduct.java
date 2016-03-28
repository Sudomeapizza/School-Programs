import java.util.Scanner;

public class IndexProduct {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the height: ");
		int arrayheight = Integer.parseInt(scan.next());
		System.out.print("Enter the width: ");
		int arraywidth = Integer.parseInt(scan.next());
		double[][] chart = new double[arrayheight][arraywidth];
		print2dArray(chart,arraywidth,arrayheight);
	}

	public static void print2dArray(double[][] array, int width, int height) {
		double tempanswer;
		for (int row = 0; row < height; row++){
			for (int col = 0; col < width; col++){
				//	sets the answer accordingly
				array[row][col] = row * col;
				//	Prints out the answers
				System.out.print(array[row][col] + " ");
			}
			System.out.println();
		}
	}
}