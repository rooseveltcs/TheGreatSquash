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

    public void moveSelf(int y, int x) {
        try {
            try {
                Obstacle moveTo = BOARD.getTileObstacle(y, x);
                if (moveTo.getPassable()) {
                    move(y,x);
                } else if(moveTo instanceof Door){
                    Door door = (Door)(moveTo);
                    door.open();
                }
            } catch (NullPointerException e) {
                BOARD.setTileCreature(LOCATION_Y, LOCATION_X, null);

                BOARD.setTileCreature(y, x, this);
                LOCATION_Y = y;
                LOCATION_X = x;
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
