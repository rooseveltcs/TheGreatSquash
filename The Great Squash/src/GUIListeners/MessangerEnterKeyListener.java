package GUIListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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
    public void keyPressed(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
            //formatOutput(messanger.getInputContents());
            try {
                messanger.getClient().sendMessage();
                messanger.clearInput();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

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

    public void formatOutput(String input) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        String header = System.getProperty("user.name") + " [" + dateFormat.format(cal.getTime()) + "]: ";
        messanger.printToDisplay(header + input);
    }
}
