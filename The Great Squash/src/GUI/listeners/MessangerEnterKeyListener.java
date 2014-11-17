package GUI.listeners;

import GUI.GameGUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import GUI.LANMessanger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessangerEnterKeyListener implements KeyListener {

    private JTextArea DISPLAY;
    private JTextField INPUT;

    public MessangerEnterKeyListener(GameGUI gui) {
        DISPLAY = gui.getChatDisplay();
        INPUT = gui.getChatInput();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getExtendedKeyCode() == 10 && !INPUT.getText().equals("")) {
            String message = formatMessage(INPUT.getText());
            INPUT.setText("");
            
            String previousText = DISPLAY.getText();
            
            DISPLAY.setText(previousText + message + "\n");
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

    private String formatMessage(String input) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        String header = System.getProperty("user.name") + " [" + dateFormat.format(cal.getTime()) + "]: ";
        
        return header + input;
    }
}
