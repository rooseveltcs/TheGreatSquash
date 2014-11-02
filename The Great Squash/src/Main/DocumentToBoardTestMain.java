/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import gameworld.Board;
import gameworld.DocumentToBoard;
import gameworld.Wall;

/**
 *
 * @author ros_dmlamarca
 */
public class DocumentToBoardTestMain {

    public static void main(String[] args) {
        Board board = DocumentToBoard.createBoard("C:\\Users\\Dylan\\Documents\\testboard.txt");
        board.show();
//        Wall shenanay = new Wall();
//        shenanay.setLocation(null, 7, 43);
//        Wall bartleby = (Wall) shenanay.clone();
//        System.out.println("Shenanay: " + shenanay.getX() + " " + shenanay.getY());
//        System.out.println("Bartleby: " + bartleby.getX() + " " + bartleby.getY());
    }
}
