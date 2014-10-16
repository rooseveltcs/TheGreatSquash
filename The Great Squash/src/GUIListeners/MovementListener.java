/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIListeners;

import GUI.TestMovementGUI;
import gameworld.Creature;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ros_dmlamarca
 */
public class MovementListener implements KeyListener {
    private Creature CREATURE;
    private TestMovementGUI GUI;
    
    public MovementListener(TestMovementGUI gui, Creature creature) {
        CREATURE = creature;
        GUI = gui;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println(ke.getExtendedKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent key) {
        int keyCode = key.getExtendedKeyCode();
        if(keyCode == 37) {
            //System.out.println("left");
            CREATURE.moveSelf(CREATURE.getY() - 1, CREATURE.getX());
            GUI.updateDisplay();
        } else if(keyCode == 38) {
            //System.out.println("up");
            CREATURE.moveSelf(CREATURE.getY(), CREATURE.getX() - 1);
            GUI.updateDisplay();
        } else if(keyCode == 39) {
            //System.out.println("right");
            CREATURE.moveSelf(CREATURE.getY() + 1, CREATURE.getX());
            GUI.updateDisplay();
        } else if(keyCode == 40) {
            //System.out.println("down");
            CREATURE.moveSelf(CREATURE.getY(), CREATURE.getX() + 1);
            GUI.updateDisplay();
        }  
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
