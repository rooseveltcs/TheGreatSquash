/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_aljacobson001
 */
public abstract class Obstacle implements Displayable {
    boolean PASSABLE;
    int LOCATION_X;
    int LOCATION_Y;
    Board BOARD;
    char SPRITE;
    
     public Obstacle(char sprite, boolean passable, Board board, int y, int x) {
        PASSABLE = passable;
        SPRITE = sprite;
        LOCATION_X = x;
        LOCATION_Y = y;
        BOARD = board;

        BOARD.getTile(y, x).setObstacle(this);
    }
     
     /**
     *
     * @return
     */
    public boolean getPassable() {
         return PASSABLE;
     }
    
    public void setPassable(boolean passable) {
        PASSABLE = passable;
    }
    
    @Override
    public void setSprite(char sprite) {
        SPRITE = sprite;
    }

    @Override
    public char displaySprite() {
        return SPRITE;
    }

    public int getX() {
        return LOCATION_X;
    }

    public int getY() {
        return LOCATION_Y;
    }
}
