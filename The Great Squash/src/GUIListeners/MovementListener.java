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
    
    public MovementListener(TestMovementGUI gui) {
        GUI = gui;
    }
    
    public void setCreature(Creature creature) {
        CREATURE = creature;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println(ke.getExtendedKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent key) {
        int keyCode = key.getExtendedKeyCode();
        if(keyCode == 37) {
            //move left
            CREATURE.moveSelf(CREATURE.getY() - 1, CREATURE.getX());
            GUI.getBoard().getClient().getHandler().sendMove(CREATURE.getY() - 1, CREATURE.getX(),CREATURE);
        } else if(keyCode == 38) {
            //move up
            CREATURE.moveSelf(CREATURE.getY(), CREATURE.getX() - 1);
            GUI.getBoard().getClient().getHandler().sendMove(CREATURE.getY(), CREATURE.getX() - 1,CREATURE);
        } else if(keyCode == 39) {
            //move right
            CREATURE.moveSelf(CREATURE.getY() + 1, CREATURE.getX());
            GUI.getBoard().getClient().getHandler().sendMove(CREATURE.getY() + 1, CREATURE.getX(),CREATURE);
        } else if(keyCode == 40) {
            //move down
            CREATURE.moveSelf(CREATURE.getY(), CREATURE.getX() + 1);
            GUI.getBoard().getClient().getHandler().sendMove(CREATURE.getY(), CREATURE.getX() + 1,CREATURE);
        }  
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
}
