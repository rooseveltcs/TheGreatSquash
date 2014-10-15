/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_dmlamarca
 */
public class Door extends Obstacle {
    private boolean DOOR_POSITION;
    private char CLOSE_SPRITE = '+';
    private char OPEN_SPRITE = '_';
    
    public Door(Board board, int y, int x) {
        super('+', false, board, y, x);
        close();
    }
    
    public void update() {
        super.setPassable(DOOR_POSITION);
        if(DOOR_POSITION) {
            super.setSprite(OPEN_SPRITE);
        } else {
            super.setSprite(CLOSE_SPRITE);
        }
    }
    
    public void open() {
        DOOR_POSITION = true;
        update();
    }
    
    public void close() {
        DOOR_POSITION = false;
        update();
    }
}
