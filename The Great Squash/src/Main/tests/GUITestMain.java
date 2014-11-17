/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.tests;

import GUI.GameGUI;
import gameworld.Board;
import gameworld.DocumentToBoard;
import gameworld.tools.CreatureCounter;
import gameworld.Inventory;
import gameworld.monsters.Monster;

/**
 *
 * @author ros_Dmlamarca
 */
public class GUITestMain {
    public static void main(String[] args) {
        GameGUI gui = new GameGUI();
        
        Monster fluffy = new Monster("liger");
        
        gui.setCreature(fluffy);
        gui.updateInventoryDisplay();
        Inventory inventory = new Inventory(27, 3, 4, 5);
        //System.out.println(inventory);
        
        Board board = DocumentToBoard.createBoard("H:\\testboard.txt");
        board.show();
        
        gui.updateBoard(board.toString());
    }
}
