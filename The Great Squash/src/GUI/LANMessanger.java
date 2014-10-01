package GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import GUIListeners.MessangerEnterKeyListener;

public class LANMessanger {
	private JFrame frame;
	private JPanel display;
	private JTextArea displayScreen;
	private JPanel input;
	private JTextField inputBox;
	private JPanel base;
	
	public LANMessanger() {
		formatInput();
		formatDisplay();
		formatFrame();
	}
	
	private void formatInput() {
		input = new JPanel();
		inputBox = new JTextField(36);
		
		input.setBounds(2, 402, 440, 70);
		//input.setBackground(Color.PINK);
		
		inputBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		inputBox.addKeyListener(new MessangerEnterKeyListener(this));
		
		input.add(inputBox);
	}
	
	private void formatDisplay() {
		display = new JPanel();
		displayScreen = new JTextArea(24,38);
		
		display.setBounds(2, 2, 440, 400);
		//display.setBackground(Color.BLUE);
		
		displayScreen.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
		
		displayScreen.setEditable(false);
		displayScreen.setLineWrap(true);
		display.add(displayScreen);
	}
	
	private void formatFrame() {
		frame = new JFrame("LAN Messanger");
		base = new JPanel();
		
		base.setBorder(BorderFactory.createBevelBorder(EtchedBorder.LOWERED));
		
		frame.setSize(450, 470);
		
		frame.add(input);
		frame.add(display);
		frame.add(base);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inputBox.requestFocusInWindow();
	}
	
	public void clearInput() {
		inputBox.setText("");
	}
	
	public String getInputContents() {
		return inputBox.getText();
	}
	
	public void printToDisplay(String string) {
		if(!displayScreen.getText().equals("")) {
			displayScreen.setText(displayScreen.getText() + "\n" + string);
		} else {
			displayScreen.setText(string);
		}
		
	}
}
