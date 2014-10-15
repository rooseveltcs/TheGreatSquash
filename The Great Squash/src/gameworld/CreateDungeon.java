/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_dmlamarca
 */
public class CreateDungeon {
    
    public static void drawRoom(Board board, int height, int width, int y, int x) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    Wall wall = new Wall(board,y + j,x + i);
                }
            }
        }
    }
}
