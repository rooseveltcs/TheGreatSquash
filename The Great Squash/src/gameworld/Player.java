/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_dmlamarca
 */
public class Player extends Creature {
    
    public Player( int y, int x, char sprite, Board board) {
         super(sprite,board,y,x);
    }
}
