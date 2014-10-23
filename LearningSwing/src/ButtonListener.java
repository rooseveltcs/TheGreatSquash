import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ButtonListener implements ActionListener {
	public Calculator calculator;
	public String value;

	public ButtonListener(Calculator calculator) {
		this.calculator = calculator;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton button = (JButton) arg0.getSource();
		//System.out.print(button.getText());//value);

		JTextField display = calculator.getDisplay();
		System.out.println(display.getText().contains("."));
		if(button.getText().equals(".")) {
			if(!display.getText().contains(".") == true) {
				display.setText(display.getText() + button.getText());
			}
		} else {
			display.setText(display.getText() + button.getText());
		}
	}

}
