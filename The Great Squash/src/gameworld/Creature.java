/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import java.util.ArrayList;

/**
 *
 * @author ros_aljacobson001
 */
//Feel free to move this class to another package. I put it here because creatures go on the board.
public abstract class Creature implements Displayable {

    char SPRITE;
    Board BOARD;
    private String NAME = "";
    int LOCATION_X;
    int LOCATION_Y;

    public Creature(char sprite, Board board, int y, int x,String name) {
        NAME = name;
        SPRITE = sprite;
        LOCATION_X = x;
        LOCATION_Y = y;
        BOARD = board;
        BOARD.setTileCreature(y, x, this);
    }

    public void moveSelf(int y, int x) {
        try {
            try {
                Obstacle moveTo = BOARD.getTileObstacle(y, x);
                if (moveTo.getPassable()) {
                    move(y, x);
                }
//                } else if(moveTo instanceof Door){
//                    Door door = (Door)(moveTo);
//                    door.open();
//                }
            } catch (NullPointerException e) {
                move(y, x);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            BOARD.setTileCreature(LOCATION_Y, LOCATION_X, this);
        }
    }

    private void move(int y, int x) {
        BOARD.setTileCreature(LOCATION_Y, LOCATION_X, null);
        BOARD.setTileCreature(y, x, this);
        LOCATION_Y = y;
        LOCATION_X = x;
    }

    @Override
    public void setSprite(char sprite) {
        SPRITE = sprite;
    }

    public ArrayList<Tile> getSurroundingTiles() {
        ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if(i != 0 && j != 0) {
                    Tile current = BOARD.getTile(LOCATION_Y + i, LOCATION_X + j);
                    surroundingTiles.add(current);
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
}
