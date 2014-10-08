/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import java.awt.Graphics;

/**
 * The board stores everything on the level
 * @author ros_aljacobson001
 */
public class Board {
    private Tile[][] GAME_BOARD;
    private int sizeX;
    private int sizeY;
    private Graphics GRAPHICS;
    
    /**
     *
     * @param x
     * @param y
     */
    public Board(int x,int y,Graphics graphics){
        GAME_BOARD = new Tile[y][x];
        sizeX = x;
        sizeY = y;
        GRAPHICS = graphics;
    }
    
    public Board(int x,int y){
        GAME_BOARD = new Tile[y][x];
        sizeX = x;
        sizeY = y;
        GRAPHICS = null;
    }
    
    public void show(){
        for(int i = 0; i < sizeX; i++) {
            for(int j = 0; j < sizeY; j++) {
                System.out.print(GAME_BOARD[j][i]);
            }
            System.out.println();
        }
    }
    
    /**
     *
     */
    public void setBoardTilesNull() {
        for(int i = 0; i < sizeX; i++) {
            for(int j = 0; j < sizeY; j++) {
                GAME_BOARD[j][i] = new Tile(null,null,null);
            }
        }
    }
    
    /**
     *
     * @return The game board
     */
    public Tile[][] getGameBoard(){
        return GAME_BOARD;
    }
    
    /**
     *
     * @param y
     * @param x
     * @return The tile specified by the x and y values given
     */
    public Tile getTile(int y,int x){
        return GAME_BOARD[y][x];
    }
    
    /**
     * Changes the specified tile (by x and y values) to the given Tile
     * @param y
     * @param x
     * @param toSetTo
     */
    public void setTile(int y,int x,Tile toSetTo){
        GAME_BOARD[y][x] = toSetTo;
    }
    
    public void setTileCreature(int y, int x, Creature creature) {
        getTile(y,x).setCreature(creature);
    }
}
