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
    
    private String LABEL;
    private boolean PASSABLE;
    private int LOCATION_X;
    private int LOCATION_Y;
    private Board BOARD;
    private char SPRITE;
    private double HEALTH;
    
    public Wall(Board board, int y, int x) {
        super('#',"",false,board,y,x);
    }

    @Override
    public String toServerData() {
        return " | " + LABEL + " " + LOCATION_Y + " " + LOCATION_X + " " + PASSABLE + " " + HEALTH + " " + SPRITE;
    }
}
