/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import LAN.TypeHolder;

/**
 *
 * @author ros_dmlamarca
 */
public class Player extends Creature {
    
    public Player(char sprite, Board board, int y, int x,String name) {
         super(sprite,board,y,x,name,TypeHolder.PLAYER);
    }
    
    public void loadFromFile(String name){
        
    }
}
