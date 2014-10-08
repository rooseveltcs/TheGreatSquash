/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import gameworld.Board;
import gameworld.Creature;
import gameworld.Player;

/**
 *
 * @author ros_dmlamarca
 */
public class TestBoard {
   public static void main(String[] args) {
       Board board = new Board(3,3);
       board.setBoardTilesNull();
       board.getTile(1, 1).setCreature(new Player('@'));
       board.show();       
   } 
}
