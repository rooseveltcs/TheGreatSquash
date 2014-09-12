import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class FactorMain {
	public static void main(String[] args) {
		run();
	}

	//Prompts the user on what is accepted input and then loops until the user inputs
	//	"quit" whilst returning the factors of any ints inputed through the console
	public static void run() {
		System.out.println("Please enter an Integer between 1 and 100 to get it's factors, or enter 2 to get their\ngreatest common denominator. When you wish to end the program simply enter \"quit\"");
		Scanner console = new Scanner(System.in);
		boolean play = true;
		while(play) {
			String currentLine = console.nextLine();
			Scanner lineScanner = new Scanner(currentLine);
			try {
				ArrayList<ArrayList<Integer>> inputFactors = new ArrayList<ArrayList<Integer>>();
				while(lineScanner.hasNext()) {
					int currentInt = lineScanner.nextInt();
					inputFactors.add(getFactors(currentInt));
				}
				if(!inputFactors.contains(null)) {
					if(inputFactors.size() == 1) {
						printFactors(inputFactors.get(0));
					} else if(inputFactors.size() == 2) {
						System.out.println(getGreatestCommonDenominator(inputFactors.get(0),inputFactors.get(1)));					
					}
				} else {
					printError();
				}
			} catch(InputMismatchException e) {
				try {
					play = testQuit(lineScanner.next());
				} catch(InputMismatchException q) {
					printError();
				}
			}
		}
	}
	
	//Takes an input of a string and returns true if it is equal to "quit" or any
	//	capitalized variation to it
	public static boolean testQuit(String consoleInput) {
		if(consoleInput.toLowerCase().equals("quit")) {
			return false;
		} else {
			printError();
			return true;
		}
	}

	//Prints an Error message telling the user the required input 
	public static void printError() {
		System.out.println("Please re-enter either an Integer between 1 and 100 (to get it's factors), or enter 2\nto get their greatest common denominator, or enter the word \"quit\" to end the program");
	}

	//Takes in a perameter of an int ant it tests whether or not the number is between 1
	//	and 100
	public static boolean testInputInt(int input) {
		if(input < 101 && input > 0) {
			return true;
		} else {
			return false;
		}
	}

	//Takes an input of an ArrayList<Integer> and prints all of the numbers out in
	//	an x,y,z format
	public static void printFactors(ArrayList<Integer> factors) {
		System.out.print(factors.get(0));
		for(int i = 1; i < factors.size(); i++) {
			System.out.print("," + factors.get(i));
		}
		System.out.println();
	}

	//Takes in a perameter of two sets of factors in an ArrayList<Integer> and
	//	output an int equal to the two factor sets highest common denominator
	public static int getGreatestCommonDenominator(ArrayList<Integer> firstFactorSet, ArrayList<Integer> secondFactorSet) {
		int GreatestCommonDenominator = 0;
		ArrayList<Integer> largerFactorSet = null;
		ArrayList<Integer> smallerFactorSet = null;
		if(firstFactorSet.size() > secondFactorSet.size()) {
			largerFactorSet = firstFactorSet;
			smallerFactorSet = secondFactorSet;
		} else {
			largerFactorSet = secondFactorSet;
			smallerFactorSet = firstFactorSet;
		}
		for(int i = 0; i < largerFactorSet.size(); i++) {
			for(int j = 0; j < smallerFactorSet.size(); j++) {
				if(largerFactorSet.get(i) == smallerFactorSet.get(j)) {
					if(largerFactorSet.get(i) > GreatestCommonDenominator) {
						GreatestCommonDenominator = largerFactorSet.get(i);
					}
				}
			}
		}
		return GreatestCommonDenominator;
	}

	//Takes an input of an integer and returns all of it's factors 
	//	in an ArrayList<Integer>
	public static ArrayList<Integer> getFactors(int toFactor) {
		if(testInputInt(toFactor)) {
			ArrayList<Integer> factors = new ArrayList<Integer>();
			double testFactor = toFactor;
			for(int i = 1; i <= toFactor; i++) {
				//System.out.println(toFactor/i + " " +testFactor/i);
				if(toFactor/i == testFactor/i) {
					factors.add(toFactor/i);
				}
			}
			return factors;
		} else {
			return null;
		}
	}
}
