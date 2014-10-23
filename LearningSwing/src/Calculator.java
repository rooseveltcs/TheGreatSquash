import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;


public class Calculator {
	private JFrame frame = new JFrame("The Great Calculator 5000");
	private ArrayList<JButton> keypadButtons = new ArrayList<JButton>();
	private JTextField screenDisplay = new JTextField(20);
	private KeyCommands keyListener;
	private JPanel operationsPanel = new JPanel();
	private JPanel keypadPanel = new JPanel();
	private JPanel displayPanel = new JPanel();
	private JPanel base = new JPanel();

	public Calculator() {
		setUpOperations();
		setUpKeypad();
		setUpDisplay();

		base.addKeyListener(keyListener);
		
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(keypadPanel);
		frame.add(displayPanel);
		frame.add(operationsPanel);
		frame.add(base);
		frame.addKeyListener(keyListener);
		frame.setVisible(true);
	}
	
	public JTextField getDisplay() {
		return screenDisplay;
	}

	private void setUpKeypad() {
		//keypadPanel.setLayout();

		for(int i = 0; i <= 9; i++) {
			keypadButtons.add(new JButton("" + i));
		}
		keypadButtons.add(new JButton("."));

		ActionListener buttonPressed = new ButtonListener(this);
		for(int i = 0; i < keypadButtons.size(); i++) {
			JButton current = keypadButtons.get(i);
			current.addKeyListener(keyListener);
			//current.setBorder(null);
			current.setMargin(new Insets(0, 0, 0, 0));
			current.setPreferredSize(new Dimension(40,25));
			current.addActionListener(buttonPressed);
			keypadPanel.add(current);
		}

		keypadPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		keypadPanel.setBounds(0,30,150,140);
		keypadPanel.addKeyListener(keyListener);
	}

	private void setUpDisplay() {
		screenDisplay.setEditable(false);
		screenDisplay.addKeyListener(keyListener);
		
		displayPanel.setBounds(0,0,300,30);
		displayPanel.add(screenDisplay);
		displayPanel.addKeyListener(keyListener);
	}

	private void setUpOperations() {
		OperationListener buttonPressed = new OperationListener(this);
		keyListener = new KeyCommands(buttonPressed);
		JButton clear = new JButton("c");
		JButton clearAll = new JButton("ce");
		JButton add = new JButton("+");
		JButton subtract = new JButton("-");
		JButton multiply = new JButton("*");
		JButton divide = new JButton("/");
		JButton power = new JButton("^");
		JButton modulus = new JButton("%");
		JButton equals = new JButton("=");

		clear.addActionListener(buttonPressed);
		clear.addKeyListener(keyListener);
		clearAll.addActionListener(buttonPressed);
		clearAll.addKeyListener(keyListener);
		add.addActionListener(buttonPressed);
		add.addKeyListener(keyListener);
		subtract.addActionListener(buttonPressed);
		subtract.addKeyListener(keyListener);
		multiply.addActionListener(buttonPressed);
		multiply.addKeyListener(keyListener);
		divide.addActionListener(buttonPressed);
		divide.addKeyListener(keyListener);
		power.addActionListener(buttonPressed);
		power.addKeyListener(keyListener);
		modulus.addActionListener(buttonPressed);
		modulus.addKeyListener(keyListener);
		equals.addActionListener(buttonPressed);
		equals.addKeyListener(keyListener);

		operationsPanel.add(clear);
		operationsPanel.add(clearAll);
		operationsPanel.add(add);
		operationsPanel.add(subtract);
		operationsPanel.add(multiply);
		operationsPanel.add(divide);
		operationsPanel.add(modulus);
		operationsPanel.add(power);
		operationsPanel.add(equals);
		operationsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		operationsPanel.setBounds(150,30,140,140);
		operationsPanel.addKeyListener(keyListener);
	}
}
