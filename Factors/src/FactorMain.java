import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class FactorMain {
	public static void main(String[] args) {
		run();
	}

	public static void run() {
		Scanner console = new Scanner(System.in);
		boolean play = true;
		while(play) {
			String currentLine = console.nextLine();
			Scanner lineScanner = new Scanner(currentLine);
			try {
				ArrayList<Integer> factors = getFactors(lineScanner.nextInt());
				printFactors(factors);
			} catch(InputMismatchException e) {
				try {
					String currentString = lineScanner.next();
					if(currentString.equals("quit") || currentString.equals("Quit")) {
						play = false;
					} else {
						System.out.println("Please re-enter either an Integer (to get it's factors), or\nenter the word \"quit\" or \"Quit\" to end the program");
					}
				} catch(InputMismatchException q) {
					System.out.println("Please re-enter either an Integer (to get it's factors), or\nenter the word \"quit\" or \"Quit\" to end the program");
				}
			}

		}

	}

	public static void printFactors(ArrayList<Integer> factors) {
		System.out.print(factors.get(0));
		for(int i = 1; i < factors.size(); i++) {
			System.out.print("," + factors.get(i));
		}
		System.out.println("\n");
	}

	public static ArrayList<Integer> getFactors(int toFactor) {
		ArrayList<Integer> factors = new ArrayList<Integer>();
		double testFactor = toFactor;
		for(int i = 1; i <= toFactor; i++) {
			//System.out.println(toFactor/i + " " +testFactor/i);
			if(toFactor/i == testFactor/i) {
				factors.add(toFactor/i);
			}
		}
		return factors;
	}
}
