/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_dmlamarca
 */
public class Wall extends Obstacle {
    public Wall(Board board, int y, int x) {
        super('#',false,board,y,x);
    }
}
