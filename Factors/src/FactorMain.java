import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class FactorMain {
	public static void main(String[] args) {
		run();
	}
	
	//Runs the factor program
	public static void run() {
		System.out.println("Please enter an Integer between 1 and 100 to get it's factors.\nWhen you wish to end the program simply enter \"quit\"");
		Scanner console = new Scanner(System.in);
		boolean play = true;
		while(play) {
			String currentLine = console.nextLine();
			Scanner lineScanner = new Scanner(currentLine);
			try {
				int currentInt = lineScanner.nextInt();
				if(currentInt < 101 && currentInt > 0) {
					ArrayList<Integer> factors = getFactors(currentInt);
					printFactors(factors);
				} else {
					System.out.println("Please re-enter either an Integer between 1 and 100 (to get it's factors), or\nenter the word \"quit\" to end the program");
				}
			} catch(InputMismatchException e) {
				try {
					String currentString = lineScanner.next();
					if(currentString.toLowerCase().equals("quit")) {
						play = false;
					} else {
						System.out.println("Please re-enter either an Integer between 1 and 100 (to get it's factors), or\nenter the word \"quit\" to end the program");
					}
				} catch(InputMismatchException q) {
					System.out.println("Please re-enter either an Integer between 1 and 100 (to get it's factors), or\nenter the word \"quit\" to end the program");
				}
			}
		}
	}

	public static void printFactors(ArrayList<Integer> factors) {
		System.out.print(factors.get(0));
		for(int i = 1; i < factors.size(); i++) {
			System.out.print("," + factors.get(i));
		}
		System.out.println();
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
