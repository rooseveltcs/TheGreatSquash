/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ros_Dmlamarca
 */
public class GameGUI {
    
    private JFrame FRAME = new JFrame("The Great Squash");
    private JPanel INVENTORY_PANEL;
    private JPanel BUTTONS_PANEL;
    private JPanel BOARD_PANEL;
    private JPanel CHAT_PANEL;
    private JPanel BASE = new JPanel();
    
    public GameGUI() {
        formatInventory();
        formatButtons();
        formatBoard();
        formatChat();
        
        formatFrame();
    }
    
    private void formatInventory() {
        INVENTORY_PANEL = new JPanel();
        INVENTORY_PANEL.setBackground(Color.PINK);
        INVENTORY_PANEL.setBounds(2, 2, 200, 500);
    }
    
    private void formatButtons() {
        BUTTONS_PANEL = new JPanel();
        BUTTONS_PANEL.setBackground(Color.BLUE);
        BUTTONS_PANEL.setBounds(204,2,738,50);
    }
    
    private void formatBoard() {
        BOARD_PANEL = new JPanel();
        BOARD_PANEL.setBackground(Color.RED);
        BOARD_PANEL.setBounds(204,54,738,448);
    }
    
    private void formatChat() {
        CHAT_PANEL = new JPanel();
        CHAT_PANEL.setBackground(Color.BLACK);
        CHAT_PANEL.setBounds(2,504,946,199);
    }
    
    private void formatFrame() {
        FRAME.add(INVENTORY_PANEL);
        FRAME.add(BUTTONS_PANEL);
        FRAME.add(BOARD_PANEL);
        FRAME.add(CHAT_PANEL);
        FRAME.add(BASE);
        
        FRAME.setSize(950, 700);
        FRAME.setVisible(true);
        FRAME.setResizable(false);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
