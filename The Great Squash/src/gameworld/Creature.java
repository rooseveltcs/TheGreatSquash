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
    
    public Creature(char sprite) {
         SPRITE = sprite;
    }
    
    public void setSprite(char sprite) {
        SPRITE = sprite;
    }
    
    public char displaySprite() { 
        return SPRITE;
    }
}
