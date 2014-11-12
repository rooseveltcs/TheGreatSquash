/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import LAN.Sendable;
import LAN.TypeHolder;
import java.util.ArrayList;

/**
 *
 * @author ros_aljacobson001
 */
//Feel free to move this class to another package. I put it here because creatures go on the board.
public abstract class Creature implements Displayable, Sendable {

    char SPRITE = 'X';
    Board BOARD;
    private String NAME = "Creature";
    private String TYPE = "abstract";
    private Inventory INVENTORY = new Inventory(2,6,5,10);
    int LOCATION_X;
    int LOCATION_Y;
    int LEVEL;
    double HEALTH = 0;
    int ENDURANCE_MODIFIER;
    double ENDURANCE;
    int SPEED_MODIFIER;
    double SPEED;
    int STRENGTH_MODIFIER;
    double STRENGTH;
    int INTELLIGENCE_MODIFIER;
    double INTELLIGENCE;
    int DEXTERITY_MODIFIER;
    double DEXTERITY;

    public Creature(char sprite, Board board, int y, int x, String name, String type) {
        TYPE = type;
        NAME = name;
        SPRITE = sprite;
        LOCATION_X = x;
        LOCATION_Y = y;
        BOARD = board;
        if (!TYPE.equals(TypeHolder.PLAYER)) {
            BOARD.getClient().getHandler().sendCreature(this);
        }
    }

    public Creature() {
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
            BOARD.getClient().getHandler().sendMove(LOCATION_Y, LOCATION_X, LOCATION_Y, LOCATION_X, this);
        }
    }

    private void move(int y, int x) {
        BOARD.getClient().getHandler().sendMove(y, x, LOCATION_Y, LOCATION_X, this);
        LOCATION_Y = y;
        LOCATION_X = x;
    }

    @Override
    public void setSprite(char sprite) {
        SPRITE = sprite;
    }

    public void interactWithSurroundings() {
        ArrayList<Tile> surroundingTiles = getSurroundingTiles();
        for (int i = 0; i < surroundingTiles.size(); i++) {
            Tile current = surroundingTiles.get(i);
            try {
                Obstacle currentObstacle = current.getObstacle();
                if (currentObstacle instanceof Interactive) {
                    Interactive currentInteractive = (Interactive) (currentObstacle);
                    currentInteractive.update();
                }
            } catch (NullPointerException ex) {
            }
        }
    }

    private ArrayList<Tile> getSurroundingTiles() {
        ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 && j != 0) {
                    try {
                        Tile current = BOARD.getTile(LOCATION_Y + i, LOCATION_X + j);
                        surroundingTiles.add(current);
                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }
        return surroundingTiles;
    }
    
    public Inventory getInventory() {
        return INVENTORY;
    }

    public void changeLevel(int change) {
        LEVEL += change;
        updateStats();
    }
    
    public int getLevel() {
        return LEVEL;
    }
    
    private void updateStats() {
        SPEED = SPEED_MODIFIER + (LEVEL * ((double)(SPEED_MODIFIER)/8));
        ENDURANCE = ENDURANCE_MODIFIER + (LEVEL * ((double)(ENDURANCE_MODIFIER)/2));
        STRENGTH = STRENGTH_MODIFIER + (LEVEL * ((double)(STRENGTH_MODIFIER)/3));
        INTELLIGENCE = INTELLIGENCE_MODIFIER + (LEVEL * ((double)(INTELLIGENCE_MODIFIER)/3));
        DEXTERITY = DEXTERITY_MODIFIER + (LEVEL * ((double)(DEXTERITY_MODIFIER)/2));
    }
    
    @Override
    public char getSprite() {
        return SPRITE;
    }
    
    public void setBoard(Board board) {
        BOARD = board;
    }

    public int getX() {
        return LOCATION_X;
    }

    public int getY() {
        return LOCATION_Y;
    }
    
    public void setX(int x) {
        LOCATION_X = x;
    }
    
    public void setY(int y) {
        LOCATION_Y = y;
    }
    
    public void setName(String toSet){
        NAME = toSet;
    }
    
    public String getName(){
        return NAME;
    }
    
    public void setType(String type){
        TYPE = type;
    }
    
    public String getType(){
        return TYPE;
    }
    
    public void setDexterityMod(int modifier) {
        DEXTERITY_MODIFIER = modifier;
    }
    
    public int getDexterityMod() {
        return DEXTERITY_MODIFIER;
    }
    
    public void setIntelligenceMod(int modifier) {
        INTELLIGENCE_MODIFIER = modifier;
    }
        
    public int getIntelligenceMod() {
        return INTELLIGENCE_MODIFIER;
    }
    
    public void setStrengthMod(int modifier) {
        STRENGTH_MODIFIER = modifier;
    }
        
    public int getStrengthMod() {
        return STRENGTH_MODIFIER;
    }
    
    public void setSpeedMod(int modifier) {
        SPEED_MODIFIER = modifier;
    }
        
    public int getSpeedMod() {
        return SPEED_MODIFIER;
    }
    
    public void setEnduranceMod(int modifier) {
        ENDURANCE_MODIFIER = modifier;
    }
        
    public int getEnduranceMod() {
        return ENDURANCE_MODIFIER;
    }
    public void setDexterity(double modifier) {
        DEXTERITY = modifier;
    }
    
    public double getDexterity() {
        return DEXTERITY;
    }
    
    public void setIntelligence(double modifier) {
        INTELLIGENCE = modifier;
    }
        
    public double getIntelligence() {
        return INTELLIGENCE;
    }
    
    public void setStrength(double modifier) {
        STRENGTH = modifier;
    }
        
    public double getStrength() {
        return STRENGTH;
    }
    
    public void setSpeed(double modifier) {
        SPEED = modifier;
    }
        
    public double getSpeed() {
        return SPEED;
    }
    
    public void setEndurance(double modifier) {
        ENDURANCE = modifier;
    }
        
    public double getEndurance() {
        return ENDURANCE;
    }
    
    
    public String toServerData(){
        return " | " + NAME + " " + LOCATION_Y + "  " + LOCATION_X + " " +  HEALTH + " " + TYPE + " " + SPRITE;
    }
}