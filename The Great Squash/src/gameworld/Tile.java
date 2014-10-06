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
    private Obstacle OBSTACLE;
    private Floor FLOOR;
    private Creature CREATURE;
    
    public Tile(Obstacle obstacle,Floor floor,Creature creature){
        OBSTACLE = obstacle;
        FLOOR = floor;
        CREATURE = creature;
    }
}
