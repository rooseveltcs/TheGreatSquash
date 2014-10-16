package GUI;

import GUIListeners.MovementListener;
import LAN.Client;
import gameworld.Board;
import gameworld.Creature;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author ros_dmlamarca
 */
public class TestMovementGUI {
    private JTextArea DISPLAY_BOX = new JTextArea();
    private JPanel DISPLAY_PANEL = new JPanel();
    private MovementListener MOVEMENT_LISTENER;
    private JFrame FRAME = new JFrame("Board Test");
    private JPanel BASE = new JPanel();   
    private Creature CREATURE;
    private Board BOARD;
    
    public TestMovementGUI(){
        BOARD = new Board(21,61,false,this);
        System.out.print("Poop on my butt and call me a sandwitch!");
        MOVEMENT_LISTENER = new MovementListener(this);
        formatDisplay();
        formatFrame();
    }
    
    private void formatDisplay() {
        DISPLAY_PANEL.setBounds(0,0,500,400);
        
        DISPLAY_BOX.setColumns(61);
        DISPLAY_BOX.setRows(21);
        DISPLAY_BOX.setPreferredSize(new Dimension(61,21));
        DISPLAY_BOX.setEditable(false);
        DISPLAY_BOX.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
        DISPLAY_BOX.addKeyListener(MOVEMENT_LISTENER);
        DISPLAY_BOX.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        DISPLAY_PANEL.addKeyListener(MOVEMENT_LISTENER);
        DISPLAY_PANEL.add(DISPLAY_BOX);
    }
    
    private void formatFrame() {
        BASE.setBorder(BorderFactory.createBevelBorder(EtchedBorder.LOWERED));
        BASE.addKeyListener(MOVEMENT_LISTENER);
        
        FRAME.addKeyListener(MOVEMENT_LISTENER);
        FRAME.add(DISPLAY_PANEL);
        FRAME.add(BASE);
        
        FRAME.setSize(new Dimension(500,400));
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.setVisible(true);
    }
    
    public void updateDisplay() {
        DISPLAY_BOX.setText(BOARD.toString());
    }
    
    public Board getBoard(){
        return BOARD;
    }
    
    public void setCreature(Creature creature){
        CREATURE = creature;
        MOVEMENT_LISTENER.setCreature(creature);
    }
}
