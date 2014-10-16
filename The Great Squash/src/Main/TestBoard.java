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
       Board board = new Board(21,61,false);
       board.setBoardTilesNull();
       Player playerBilly = new Player((char)(178),board,6,6);
 
       CreateDungeon.drawRoom(board, 11, 11, 1, 1);
       Door door = new Door(board, 6, 11);
       
       // 198 = Æ
       //  64 = @
       board.show();  
       TestMovementGUI gui = new TestMovementGUI(board, playerBilly);
       
       gui.updateDisplay();
   } 
   
   public static void testMoveConsole() {
       Scanner console = new Scanner(System.in);
       
       Board board = new Board(3,3,false);
       board.setBoardTilesNull();
       Player playerBilly = new Player('@',board,1,1);
       board.show();  
       
       playerBilly.moveSelf(console.nextInt(), console.nextInt());
       
       board.show();
   }
}
