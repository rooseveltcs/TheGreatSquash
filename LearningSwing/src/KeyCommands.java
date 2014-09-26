import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.NoSuchElementException;

import javax.swing.JTextField;


public class KeyCommands implements KeyListener {
	private double valueOne;
	private double valueTwo;
	private char operation;
	OperationListener operations;
	JTextField display;

	public KeyCommands(OperationListener operationListener) {
		this.operations = operationListener;
		this.display = operations.display;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyValue = arg0.getExtendedKeyCode();
		char keyChar = arg0.getKeyChar();
		System.out.println(keyValue + " - " + ((int)(keyChar)));
		if(57 >=  keyChar && keyChar >= 48 ) {
			//System.out.println("num");
			display.setText(display.getText() + arg0.getKeyChar());
		} else if(keyChar == 46) {
			if(!display.getText().contains(".")) {
				display.setText(display.getText() + arg0.getKeyChar());
			}
		}
		if(keyChar == '+' || keyChar == '-' || keyChar == '%' || keyChar == '^' || keyChar == '*' || keyChar == '/') {
			//System.out.println("operation");
			operations.valueOne = operations.getValue();
			operations.operation = keyChar;
			System.out.println(valueOne);
			operations.clearDisplay();
		} else if(keyChar == '=' || keyValue == 10) {
			//System.out.println("enter");
			operations.valueTwo = operations.getValue();
			System.out.println(operations.valueOne + "|" + operations.valueTwo);
			double answer = operations.solveOperations(operations.valueOne, operations.valueTwo, operations.operation);
			operations.printToDisplay(answer);
		} else if(keyValue == 8) {
			//System.out.println("back");
			try {
			String text = display.getText();
			display.setText(text.substring(0,text.length() - 1));
			} catch(StringIndexOutOfBoundsException e) {
				System.out.println("Back error");
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
