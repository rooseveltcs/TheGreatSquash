/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.TestMovementGUI;
import gameworld.Board;
import gameworld.Creature;
import gameworld.Player;
import java.util.Scanner;

/**
 *
 * @author ros_dmlamarca
 */
public class TestBoard {
   public static void main(String[] args) {
       Board board = new Board(21,61);
       board.setBoardTilesNull();
       Player playerBilly = new Player(1,1,'@',board);
       board.show();  
       TestMovementGUI gui = new TestMovementGUI(board, playerBilly);
       
       gui.updateDisplay();
   } 
   
   public static void testMoveConsole() {
       Scanner console = new Scanner(System.in);
       
       Board board = new Board(3,3);
       board.setBoardTilesNull();
       Player playerBilly = new Player(1,1,'@',board);
       board.show();  
       
       playerBilly.moveSelf(console.nextInt(), console.nextInt());
       
       board.show();
   }
}
