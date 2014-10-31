/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import LAN.Sendable;
import java.util.ArrayList;

/**
 *
 * @author ros_aljacobson001
 */
//Feel free to move this class to another package. I put it here because creatures go on the board.
public abstract class Creature implements Displayable,Sendable {

    char SPRITE;
    Board BOARD;
    private String NAME = "Creature";
    int LOCATION_X;
    int LOCATION_Y;
    double HEALTH;
    private String TYPE = "abstract";

    public Creature(char sprite, Board board, int y, int x,String name) {
        NAME = name;
        SPRITE = sprite;
        LOCATION_X = x;
        LOCATION_Y = y;
        BOARD = board;
        BOARD.getClient().getHandler().sendMove(y, x,0,0, this);
    }

    public void moveSelf(int y, int x) {
        try {
            try {
                Obstacle moveTo = BOARD.getTileObstacle(y, x);
                if (moveTo.getPassable()) {
                    move(y, x);
                }
            } catch (NullPointerException e) {
                move(y, x);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            BOARD.getClient().getHandler().sendMove(LOCATION_Y,LOCATION_X,LOCATION_Y,LOCATION_X, this);
        }
    }

    private void move(int y, int x) {
        BOARD.getClient().getHandler().sendMove(y,x,LOCATION_Y,LOCATION_X,this);
        LOCATION_Y = y;
        LOCATION_X = x;
    }

    @Override
    public void setSprite(char sprite) {
        SPRITE = sprite;
    }
    
    public void interactWithSurroundings() {
        ArrayList<Tile> surroundingTiles = getSurroundingTiles();
        for(int i = 0; i < surroundingTiles.size(); i++) {
            Tile current = surroundingTiles.get(i);
            try {
                Obstacle currentObstacle = current.getObstacle();
                if(currentObstacle instanceof Interactive) {
                    Interactive currentInteractive = (Interactive)(currentObstacle);
                    currentInteractive.update();
                }
            } catch(NullPointerException ex) {}
        }
    }

    private ArrayList<Tile> getSurroundingTiles() {
        ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if(i != 0 && j != 0) {
                    try {
                        Tile current = BOARD.getTile(LOCATION_Y + i, LOCATION_X + j);
                        surroundingTiles.add(current);
                    } catch(IndexOutOfBoundsException e){}
                }
            }
        }
        return surroundingTiles;
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
    
    public void setName(String toSet){
        NAME = toSet;
    }
    
    public String getName(){
        return NAME;
    }
    
    public void setType(String toSet){
        TYPE = toSet;
    }
    
    public String getType(){
        return TYPE;
    }
    
    public String toServerData(){
        return " | " + NAME + " " + LOCATION_Y + "  " + LOCATION_X + " " +  HEALTH + " " + TYPE + " " + SPRITE;
    }
}