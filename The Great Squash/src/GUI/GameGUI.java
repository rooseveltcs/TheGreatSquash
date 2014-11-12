/*
 * I'm watching you o_0
 */
package GUI;

import gameworld.Creature;
import gameworld.Inventory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author ros_Dmlamarca
 */
public class GameGUI {

    private Border PANEL_BORDER = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
    private Border DISPLAY_BORDER = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    private Font DISPLAY_FONT = new Font("Monospaced", Font.PLAIN, 12);
    private JFrame FRAME = new JFrame("The Great Squash");
    private JTextArea INVENTORY_DISPLAY;
    private JPanel INVENTORY_PANEL;
    private JPanel BUTTONS_PANEL;
    private JTextArea BOARD_DISPLAY;
    private JPanel BOARD_PANEL;
    private JTextArea CHAT_DISPLAY;
    private JPanel CHAT_PANEL;
    private JPanel BASE = new JPanel();
    
    private Creature CONTAINED_CREATURE;

    public GameGUI() {
        formatInventory();
        formatButtons();
        formatBoard();
        formatChat();

        formatFrame();
    }

    private void formatInventory() {
        INVENTORY_DISPLAY = new JTextArea(27,26);
        INVENTORY_DISPLAY.setFont(DISPLAY_FONT);
        INVENTORY_DISPLAY.setPreferredSize(new Dimension(27,26));
        INVENTORY_DISPLAY.setLineWrap(true);
        INVENTORY_DISPLAY.setBorder(DISPLAY_BORDER);
        
        INVENTORY_PANEL = new JPanel();
        //INVENTORY_PANEL.setBackground(Color.PINK);
        INVENTORY_PANEL.setBounds(2, 2, 200, 500);
        INVENTORY_PANEL.setBorder(BorderFactory.createTitledBorder(PANEL_BORDER,"Inventory"));
        INVENTORY_PANEL.add(INVENTORY_DISPLAY);
    }
    
    public void updateInventoryDisplay() {
        Inventory creatureInventory = CONTAINED_CREATURE.getInventory();
        INVENTORY_DISPLAY.setText(creatureInventory.toString());
    }

    private void formatButtons() {
        BUTTONS_PANEL = new JPanel();
        //BUTTONS_PANEL.setBackground(Color.BLUE);
        BUTTONS_PANEL.setBounds(204, 2, 738, 50);
        BUTTONS_PANEL.setBorder(PANEL_BORDER);
    }

    private void formatBoard() {
        BOARD_DISPLAY = new JTextArea(20,20);
        BOARD_DISPLAY.setFont(DISPLAY_FONT);
        BOARD_DISPLAY.setPreferredSize(new Dimension(20,20));
        BOARD_DISPLAY.setLineWrap(true);
        BOARD_DISPLAY.setBorder(DISPLAY_BORDER);
        
        BOARD_PANEL = new JPanel();
        //BOARD_PANEL.setBackground(Color.RED);
        BOARD_PANEL.setBounds(204, 54, 738, 448);
        BOARD_PANEL.setBorder(PANEL_BORDER);
        BOARD_PANEL.add(BOARD_DISPLAY);
    }

    private void formatChat() {
        CHAT_DISPLAY = new JTextArea(20,20);
        CHAT_DISPLAY.setFont(DISPLAY_FONT);
        CHAT_DISPLAY.setPreferredSize(new Dimension(20,20));
        CHAT_DISPLAY.setLineWrap(true);
        CHAT_DISPLAY.setBorder(DISPLAY_BORDER);
        
        CHAT_PANEL = new JPanel();
        //CHAT_PANEL.setBackground(Color.BLACK);
        CHAT_PANEL.setBounds(2, 504, 940, 170);
        CHAT_PANEL.setBorder(PANEL_BORDER);
        CHAT_PANEL.add(CHAT_DISPLAY);
    }

    private void formatFrame() {
        FRAME.add(INVENTORY_PANEL);
        FRAME.add(BUTTONS_PANEL);
        FRAME.add(BOARD_PANEL);
        FRAME.add(CHAT_PANEL);
        
        //BASE.setBorder(BASE_BORDER);
        FRAME.add(BASE);

        FRAME.setSize(950, 700);
        FRAME.setVisible(true);
        FRAME.setResizable(false);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void setCreature(Creature creature) {
        CONTAINED_CREATURE = creature;
    }
}
