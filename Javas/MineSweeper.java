package minegrid;

import java.util.Scanner;

public class MineSweeper {
	private static int bnewanswer = 0;
	private static int la = 0;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Spot[][] grid = null;
		boolean ok = false;
		while (ok != true) {
			System.out.println("What will the width of your grid?");
			String answer = scan.next();
			System.out.println("What will the length of your grid?");
			String answer1 = scan.next();
			try {
				int newanswer = Integer.parseInt(answer);
				int newanswer1 = Integer.parseInt(answer1);
				if (newanswer != 0 && newanswer1 != 0) {
					while (ok != true) {
						System.out.println(
								"How many mines do you want? (put \"challenge\") for the computer to put the preferred amout of bombs");
						String banswer = scan.next();
						if (banswer.equalsIgnoreCase("challenge")) {
							bnewanswer = ((newanswer + newanswer1) / 2) * 3;
							grid = new Spot[newanswer][newanswer1];
							System.out.println(grid.length + " " + grid[0].length);
							makeTheGrid(grid);
							putTheBombs(grid, bnewanswer);
							ok = true;
						} else {
							try {
								bnewanswer = Integer.parseInt(banswer);
								if (bnewanswer != 0) {
									if (bnewanswer == (newanswer * newanswer1)) {
										System.out.println("Sorry but you have made the game 100% impossible! Put in a value that will make the game less than 100%. (like 99.99% possible! (So like put one less (heck that is what I will do :P)))");
										ok = true;
										grid = new Spot[newanswer1][newanswer];
										makeTheGrid(grid);
										putTheBombs(grid, (newanswer * newanswer1) - 1);
										System.out.println("There is now " + ((newanswer * newanswer1) - 1) + " bombs");
									} else {
										System.out.println(bnewanswer + " " + newanswer + " " + newanswer1 + " " + (newanswer * newanswer1));
										if (bnewanswer <= (newanswer * newanswer1)) {
											grid = new Spot[newanswer1][newanswer];
											makeTheGrid(grid);
											putTheBombs(grid, bnewanswer);
											ok = true;
										} else {
											System.out.println(
													"You can't put that many bombs into the grid! The last bomb was thrown at you!");
											System.exit(0);
										}
										
									}
								} else {
									System.out.println(
											"Sorry, but I disabled being able to put no bombs in the grid. It makes the game completly pointless!");
								}
							} catch (NumberFormatException e) {
								System.out.println("Put in an Integer, not anything else.");
							}
						}
					}
				} else {
					System.out.println("Can't to zero!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Put in an Integer, not anything else.");
			}
		}
		ok = false;

		System.out.println("Enter in cordinates like this \"1,1\"");
		System.out.println("your range that you can go is \"1,1\" to \"" + grid.length + "," + grid[0].length + "\"");
		System.out.println("The cordinates is like x,y");

		boolean end = false;
		while (end != true) {
			printGrid(grid);
			la = 0;
			System.out.print("Cordinates: ");
			try {
				String input = scan.next();
				if (input.equals("42")) {
					showTheBombedGrid(grid);
				} else if (input.equalsIgnoreCase("flag")) {
					System.out.print("Enter a spot to flag or unflag: ");
					String inputForFlagging = scan.next();
					try {
						String[] result = new String[2];
						result = inputForFlagging.split(",");
						int one = Integer.parseInt(result[0]) - 1;
						int two = Integer.parseInt(result[1]) - 1;
						if (one >= 0 && two >= 0 && one < grid.length && two < grid[0].length) {
							if (grid[one][two].getvalue().equals(" * ")) {
								System.out.println("Flagged " + one + "," + two);
								grid[one][two].setvalue("F");
							} else if (grid[one][two].getvalue().equals(" F ")) {
								System.out.println("Unflagged " + one + "," + two);
								grid[one][two].setvalue("*");
							} else {
								System.out.println(grid[one][two].getvalue());
								System.out.println("UH OH!");
							}
						} else {
							System.out.println("Sorry that is out of bounds!");
						}
					} catch (NumberFormatException e) {
						System.out.println("Integers please, Try flagging again!");
					}
				} else {
					try {
						String[] result = input.split(",");
						if (result.length == 2) {
							int one = Integer.parseInt(result[0]) - 1;
							int two = Integer.parseInt(result[1]) - 1;

							if (one >= 0 && two >= 0 && one < grid.length && two < grid[0].length) {
								if (grid[one][two].getvalue().equals(" * ")) {
									if (input.equalsIgnoreCase("Done")) {
										System.exit(0);
									} else if (one % 1 == 0 && two % 1 == 0) {
										if (grid[one][two].isBomb() == true) {
											end = true;
											System.out.println("You hit a bomb!");
											showTheBombedGrid(grid);
											System.out.println("Game Over!");
										} else {
											checkSurroundingSpots(grid, one, two);
											if (remaningSpaces(grid, (grid.length * grid[0].length) - bnewanswer)) {
												showTheBombedGrid(grid);
												System.out.println();
												System.out.println("You won!");
												end = true;
											}
										}
									}
								} else if (grid[one][two].getvalue().equals(" F ")) {
									System.out.println("You flagged that spot! unflag it if you want to go there.");
								} else {
									System.out.println("You cant go into a spot that is already opened!");
								}
							} else {
								System.out.println("Sorry that is out of bounds!");
							}
						} else {
							System.out.println("Two cordinates please!");
						}
					} catch (NumberFormatException e) {
						System.out.println("Integers please!");
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Put it like dis -> \"x,y\"");
			}
		}
	}

	public static boolean remaningSpaces(Spot[][] grid, int amount) {
		boolean check = false;
		int counter = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!grid[i][j].getvalue().equals(" * ") && !grid[i][j].getvalue().equals(" F ")) {
					// lala();
					counter++;
				}
			}
		}
		if (counter == amount) {
			check = true;
		}
		return check;
	}

	public static void lala() {
		la++;
		System.out.println(la + " are is opened!");
	}

	public static void open(Spot[][] grid, int x, int y) {
		if (x - 1 >= 0 && y >= 0 && x - 1 < grid.length && y < grid[0].length
				&& !grid[x - 1][y].getvalue().equals(" . ") && !grid[x - 1][y].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x - 1, y);
		}
		if (x - 1 >= 0 && y - 1 >= 0 && x - 1 < grid.length && y - 1 < grid[0].length
				&& !grid[x - 1][y - 1].getvalue().equals(" . ") && !grid[x - 1][y - 1].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x - 1, y - 1);
		}
		if (x >= 0 && y - 1 >= 0 && x < grid.length && y - 1 < grid[0].length
				&& !grid[x][y - 1].getvalue().equals(" . ") && !grid[x][y - 1].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x, y - 1);
		}
		if (x + 1 >= 0 && y - 1 >= 0 && x + 1 < grid.length && y - 1 < grid[0].length
				&& !grid[x + 1][y - 1].getvalue().equals(" . ") && !grid[x + 1][y - 1].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x + 1, y - 1);
		}
		if (x + 1 >= 0 && y >= 0 && x + 1 < grid.length && y < grid[0].length
				&& !grid[x + 1][y].getvalue().equals(" . ") && !grid[x + 1][y].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x + 1, y);
		}
		if (x + 1 >= 0 && y + 1 > 0 && x + 1 < grid.length && y + 1 < grid[0].length
				&& !grid[x + 1][y + 1].getvalue().equals(" . ") && !grid[x + 1][y + 1].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x + 1, y + 1);
		}
		if (x >= 0 && y + 1 >= 0 && x < grid.length && y + 1 < grid[0].length
				&& !grid[x][y + 1].getvalue().equals(" . ") && !grid[x][y + 1].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x, y + 1);
		}
		if (x - 1 >= 0 && y + 1 >= 0 && x - 1 < grid.length && y + 1 < grid[0].length
				&& !grid[x - 1][y + 1].getvalue().equals(" . ") && !grid[x - 1][y + 1].getvalue().equals(" F ")) {
			checkSurroundingSpots(grid, x - 1, y + 1);
		}
	}

	public static void checkSurroundingSpots(Spot[][] grid, int x, int y) {
		int counter = 0;

		if (x - 1 >= 0 && y >= 0 && x - 1 < grid.length && y < grid[0].length && grid[x - 1][y].isBomb()) {
			// System.out.println("left");
			counter++;
		}
		if (x - 1 >= 0 && y - 1 >= 0 && x - 1 < grid.length && y - 1 < grid[0].length && grid[x - 1][y - 1].isBomb()) {
			// System.out.println("top left");
			counter++;
		}
		if (x >= 0 && y - 1 >= 0 && x < grid.length && y - 1 < grid[0].length && grid[x][y - 1].isBomb()) {
			// System.out.println("top");
			counter++;
		}
		if (x + 1 >= 0 && y - 1 >= 0 && x + 1 < grid.length && y - 1 < grid[0].length && grid[x + 1][y - 1].isBomb()) {
			// System.out.println("top right");
			counter++;
		}
		if (x + 1 >= 0 && y >= 0 && x + 1 < grid.length && y < grid[0].length && grid[x + 1][y].isBomb()) {
			// System.out.println("right");
			counter++;
		}
		if (x + 1 >= 0 && y + 1 >= 0 && x + 1 < grid.length && y + 1 < grid[0].length && grid[x + 1][y + 1].isBomb()) {
			// System.out.println("bottom right");
			counter++;
		}
		if (x >= 0 && y + 1 >= 0 && x < grid.length && y + 1 < grid[0].length && grid[x][y + 1].isBomb()) {
			// System.out.println("bottom");
			counter++;
		}
		if (x - 1 >= 0 && y + 1 >= 0 && x - 1 < grid.length && y + 1 < grid[0].length && grid[x - 1][y + 1].isBomb()) {
			// System.out.println("bottom left");
			counter++;
		}
		if (counter == 0) {
			grid[x][y].setvalue(".");
			open(grid, x, y);
		} else {
			grid[x][y].setvalue(String.valueOf(counter));
		}
	}

	public static void putTheBombs(Spot[][] grid, int amnt) {
		boolean[][] grids = new boolean[grid.length][grid[0].length];
		for (int i = 0; i < amnt;) {
			int bomby = (int) (Math.random() * grid.length);
			int bombyy = (int) (Math.random() * grid[0].length);
			if (grids[bomby][bombyy] == false) {
				grid[bomby][bombyy].makeABomb(true);
				grids[bomby][bombyy] = true;
				i++;
			}
		}
	}

	public static void makeTheGrid(Spot[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = new Spot(j, i);
				grid[i][j].setvalue("*");
			}
		}
	}

	public static void showTheBombedGrid(Spot[][] grid) {
		Spot[][] gridded = new Spot[grid.length][grid[0].length];
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				gridded[j][i] = new Spot(grid[j][i].getX(), grid[j][i].getY());
				gridded[j][i].setvalue(grid[j][i].getvalue());
				gridded[j][i].makeABomb(false);
				if (grid[j][i].isBomb()) {
					gridded[j][i].makeABomb(true);
				}
			}
		}

		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (gridded[j][i].isBomb() == true) {
					System.out.print(" X ");
				} else {
					checkSurroundingSpots2(gridded, j, i);
					System.out.print(gridded[j][i].getvalue());
				}
			}
			System.out.println();
		}
	}

	public static void checkSurroundingSpots2(Spot[][] gridded, int x, int y) {
		int counter = 0;

		if (x - 1 >= 0 && y >= 0 && x - 1 < gridded.length && y < gridded[0].length && gridded[x - 1][y].isBomb()) {
			// System.out.println("left");
			counter++;
		}
		if (x - 1 >= 0 && y - 1 >= 0 && x - 1 < gridded.length && y - 1 < gridded[0].length
				&& gridded[x - 1][y - 1].isBomb()) {
			// System.out.println("top left");
			counter++;
		}
		if (x >= 0 && y - 1 >= 0 && x < gridded.length && y - 1 < gridded[0].length && gridded[x][y - 1].isBomb()) {
			// System.out.println("top");
			counter++;
		}
		if (x + 1 >= 0 && y - 1 >= 0 && x + 1 < gridded.length && y - 1 < gridded[0].length
				&& gridded[x + 1][y - 1].isBomb()) {
			// System.out.println("top right");
			counter++;
		}
		if (x + 1 >= 0 && y >= 0 && x + 1 < gridded.length && y < gridded[0].length && gridded[x + 1][y].isBomb()) {
			// System.out.println("right");
			counter++;
		}
		if (x + 1 >= 0 && y + 1 >= 0 && x + 1 < gridded.length && y + 1 < gridded[0].length
				&& gridded[x + 1][y + 1].isBomb()) {
			// System.out.println("bottom right");
			counter++;
		}
		if (x >= 0 && y + 1 >= 0 && x < gridded.length && y + 1 < gridded[0].length && gridded[x][y + 1].isBomb()) {
			// System.out.println("bottom");
			counter++;
		}
		if (x - 1 >= 0 && y + 1 >= 0 && x - 1 < gridded.length && y + 1 < gridded[0].length
				&& gridded[x - 1][y + 1].isBomb()) {
			// System.out.println("bottom left");
			counter++;
		}
		if (counter == 0) {
			gridded[x][y].setvalue(".");
		} else {
			gridded[x][y].setvalue(String.valueOf(counter));
		}
	}

	public static void printGrid(Spot[][] grid) {
		System.out.println();
		System.out.print("    ");
		for (int i = 0; i < grid.length; i++) {
			if ((i + 1) >= 10) {
				System.out.print(" " + (i + 1));
			} else {
				System.out.print(" " + (i + 1) + " ");
			}
		}
		System.out.println();
		System.out.print("    ");
		for (int i = 0; i < grid.length; i++) {
			System.out.print(" ↓ ");
		}
		System.out.println();
		System.out.println();
		for (int i = 0; i < grid[0].length; i++) {
			String a = i + 1 + ")";
			int b = 4 - a.length();
			for (int c = 0; c < b; c++) {
				System.out.print(" ");
			}
			System.out.print(a);

			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[j][i].getvalue());
			}
			System.out.println();
		}
		System.out.println();
	}
}
