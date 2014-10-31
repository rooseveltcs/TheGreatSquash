/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import gameworld.Board;
import gameworld.DocumentToBoard;

/**
 *
 * @author ros_dmlamarca
 */
public class DocumentToBoardTestMain {
    public static void main(String[] args) {
        Board board = DocumentToBoard.createBoard("H:\\testboard.txt");
    }
}
