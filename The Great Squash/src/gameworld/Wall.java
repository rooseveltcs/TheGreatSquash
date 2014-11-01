/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_dmlamarca
 */
public class Wall extends Obstacle implements Cloneable {
    public Wall(Board board, int y, int x) {
        super('#',"",false,board,y,x);
    }
    
    public Wall() {
        super('#',"",false);
    }
    
    public Wall clone() {
        Wall clone = new Wall();
        clone.setLocation(BOARD, LOCATION_Y, LOCATION_X);
        return clone;
    }

    @Override
    public String toServerData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
