package GUI;

import GUIListeners.MovementListener;
import java.awt.Dimension;
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
    
    public TestMovementGUI(){
        MOVEMENT_LISTENER = new MovementListener(this);
        formatDisplay();
        formatFrame();
    }
    
    private void formatDisplay() {
        DISPLAY_PANEL.setBounds(0,0,500,400);
        
        DISPLAY_BOX.setColumns(41);
        DISPLAY_BOX.setRows(21);
        DISPLAY_BOX.setEditable(false);
        DISPLAY_BOX.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)));
        DISPLAY_BOX.addKeyListener(MOVEMENT_LISTENER);
        
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
}
