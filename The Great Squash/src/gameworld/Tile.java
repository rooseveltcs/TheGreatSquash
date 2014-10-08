/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

/**
 *
 * @author ros_aljacobson001
 */
public class Tile {
    private char EMPTY_SPACE_SPRITE = '.';
    private Obstacle OBSTACLE;
    private Floor FLOOR;
    private Creature CREATURE;

    public Tile(Obstacle obstacle, Floor floor, Creature creature) {
        OBSTACLE = obstacle;
        FLOOR = floor;
        CREATURE = creature;
    }
    
    public void setCreature(Creature creature) {
        CREATURE = creature;
    }

    @Override
    public String toString() {
        String output = "";
        if (CREATURE != null) {
            output += CREATURE.displaySprite();
        } else if(OBSTACLE != null){
            output += OBSTACLE.displaySprite();
        } else if(FLOOR != null){
            output += FLOOR.displaySprite();
        } else {
            output += EMPTY_SPACE_SPRITE;
        }
        return output;
    }
}