import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextField;


public class OperationListener implements ActionListener {
	public Calculator calculator;
	public JTextField display;
	public boolean finish = false;
	public double valueOne;
	public double valueTwo;
	public char operation;

	public OperationListener(Calculator calculator) {
		this.calculator = calculator;
		this.display = calculator.getDisplay();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton button = (JButton) arg0.getSource();
		String buttonLabel = button.getText();
		if(buttonLabel.equals("=")) {
			valueTwo = getValue();
			System.out.println(valueTwo);
			clearDisplay();
			finish = true;
		} else if(buttonLabel.equals("ce")) {
			clearAll();
		} else if(buttonLabel.equals("c")) {
			clearDisplay();
		} else {
			getOperation(buttonLabel.charAt(0));
		}

		if(finish) {
			double answer = solveOperations(valueOne,valueTwo,operation);
			printToDisplay(answer);
			finish = false;
			clearValues();
		}

	}

	public void printToDisplay(double answer) {
		if((int)(answer) == answer) {
			display.setText((int)(answer) + "");
		} else {
			display.setText(answer + "");
		}
	}

	public void getOperation(char operation) {
		valueOne = getValue();
		System.out.println(valueOne);
		System.out.println(operation);
		this.operation = operation;
		clearDisplay();
	}

	public void clearDisplay() {
		display.setText("");
	}

	public void clearAll() {
		clearDisplay();
		clearValues();
	}

	public void clearValues() {
		valueOne = 0;
		valueTwo = 0;
		operation = ' ';
	}

	public double solveOperations(double valueOne, double valueTwo, char operation) {
		System.out.println(operation + "| op");
		if(operation == '+') {
			return valueOne + valueTwo;
		} else if(operation == '-') {
			return valueOne - valueTwo;
		} else if(operation == '*') {
			return valueOne * valueTwo;
		} else if(operation == '/') {
			return valueOne / valueTwo;
		} else if(operation == '^') {
			return Math.pow(valueOne, valueTwo);
		} else if(operation == '%') {
			return valueOne % valueTwo;
		} else {
			System.out.println(valueOne + "" + operation + "" + valueTwo);
			return 666.0;
		}
	}

	public double getValue() {
		Scanner valueExtract = new Scanner(display.getText());
		double value = 0;
		try {
			value = valueExtract.nextDouble();
		} catch(NoSuchElementException e) {}
		System.out.println(value);
		return value;
	}
}
