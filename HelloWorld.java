public class HelloWorld {
	public static void main(String[] args) {
		System.out.println();

		String[] names = {"Billy","Bob","Joe","Willam","John","Jonathon","Johnny"};
		//String[] names = {"Billy","Bob"};
		//String[] names = {"Billy"};
		boolean end = false;
		int i = 1;

		System.out.print("Hello " + names[0]);

		while (end == false) {
			if (names.length == 1){
				end = true;
			} else {
				if ((names.length) - i >= 2) {
					System.out.print(", ");
					System.out.print(names[i]);
				} else {
					System.out.print(" and ");
					end = true;
					System.out.print(names[i]);
				}

			}

			i++;
			if (i >= names.length){
				end = true;
			}
		}

		System.out.println(".");
		System.out.println();
	}
}