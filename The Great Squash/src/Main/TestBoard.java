/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.TestMovementGUI;
import gameworld.Board;
import gameworld.CreateDungeon;
import gameworld.Creature;
import gameworld.Door;
import gameworld.Player;
import gameworld.Wall;
import java.util.Scanner;

/**
 *
 * @author ros_dmlamarca
 */
public class TestBoard {
   public static void main(String[] args) {
       TestMovementGUI gui = new TestMovementGUI();
       Board board = gui.getBoard();
       board.setBoardTilesNull();
       Player playerBilly = new Player((char)(198),board,6,6);
       gui.setCreature(playerBilly);
 
       //CreateDungeon.drawRoom(board, 11, 11, 1, 1);
       //Door door = new Door(board, 6, 11);
       
       // 198 = Æ
       // 223 = ß
       //  64 = @
       board.show();  
       
       gui.updateDisplay();
   } 
}
