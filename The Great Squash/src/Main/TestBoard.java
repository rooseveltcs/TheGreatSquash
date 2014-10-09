/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
       Scanner console = new Scanner(System.in);
       
       Board board = new Board(3,3);
       board.setBoardTilesNull();
       Player playerBilly = new Player(1,1,'@',board);
       board.show();  
       
       playerBilly.moveSelf(console.nextInt(), console.nextInt());
       
       board.show();
   } 
}
