package GUIListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import GUI.LANMessanger;

public class MessangerEnterKeyListener implements KeyListener {
	private LANMessanger messanger;
	
	public MessangerEnterKeyListener(LANMessanger messanger) {
		this.messanger = messanger; 
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getExtendedKeyCode() == 10) {
			String input = messanger.getInputContents();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			
			String header = System.getProperty("user.name") + " [" + dateFormat.format(cal.getTime()) + "]: ";
			messanger.clearInput();
			messanger.printToDisplay(header + input);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
