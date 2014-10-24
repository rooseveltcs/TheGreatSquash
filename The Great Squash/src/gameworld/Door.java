/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_dmlamarca
 */
public class Door extends Obstacle implements Interactive {
    private boolean DOOR_POSITION;
    private char CLOSE_SPRITE = '+';
    private char OPEN_SPRITE = '_';
    
    public Door(Board board, int y, int x) {
        super('+',"",false, board, y, x);
        DOOR_POSITION = false;
    }
    
    public void update() {
        if(DOOR_POSITION) {
            super.setSprite(CLOSE_SPRITE);
            super.setPassable(false);
        } else {
            super.setSprite(OPEN_SPRITE);
            super.setPassable(true);
        }
    }
}
