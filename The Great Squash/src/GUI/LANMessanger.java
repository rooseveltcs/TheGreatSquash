package GUI;

import javax.swing.JFrame;

public class LANMessanger {
	private JFrame frame;
	
	public LANMessanger() {
		frame = new JFrame("LAN Messanger");
		frame.setSize(500, 550);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
