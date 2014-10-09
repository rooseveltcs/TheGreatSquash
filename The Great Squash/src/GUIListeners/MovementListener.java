/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIListeners;

import GUI.TestMovementGUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ros_dmlamarca
 */
public class MovementListener implements KeyListener {
    private TestMovementGUI GUI;
    
    public MovementListener(TestMovementGUI gui) {
        GUI = gui;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent key) {
        System.out.println(key.getExtendedKeyCode());
        switch(key.getExtendedKeyCode()) {
            case 37:
                
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
