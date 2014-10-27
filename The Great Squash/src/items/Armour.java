/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import gameworld.Displayable;
import items.Item;

/**
 *
 * @author ros_dmlamarca
 */
public abstract class Armour implements Item, Displayable {
    private char SPRITE = (char)(177);
    // 177 = ±
    
    public void setSprite(char sprite) {
        SPRITE = sprite;
    }
    
    public char displaySprite() {
        return SPRITE;
    }
}
