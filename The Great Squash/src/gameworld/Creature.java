/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_aljacobson001
 */
//Feel free to move this class to another package. I put it here because creatures go on the board.
public abstract class Creature implements Displayable {

    char SPRITE;
    Board BOARD;
    int LOCATION_X;
    int LOCATION_Y;
    
    public Creature(char sprite, Board board, int y, int x) {
        SPRITE = sprite;
        LOCATION_X = x;
        LOCATION_Y = y;
        BOARD = board;
        
        BOARD.setTileCreature(y, x, this);
    }
    
    public void moveSelf() {
        
    }
    
    public void setSprite(char sprite) {
        SPRITE = sprite;
    }
    
    public char displaySprite() {        
        return SPRITE;
    }
}
