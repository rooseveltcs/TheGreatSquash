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
    private Tile[][] THE_GAME_BOARD;
    private Graphics GRAPHICS;
    
    /**
     *
     * @param x
     * @param y
     */
    public Board(int x,int y,Graphics graphics){
        THE_GAME_BOARD = new Tile[y][x];
        GRAPHICS = graphics;
    }
    
    public void show(){
        //to implement
    }
    
    /**
     *
     * @return The game board
     */
    public Tile[][] getGameBoard(){
        return THE_GAME_BOARD;
    }
    
    /**
     *
     * @param y
     * @param x
     * @return The tile specified by the x and y values given
     */
    public Tile getTile(int y,int x){
        return THE_GAME_BOARD[y][x];
    }
    
    /**
     * Changes the specified tile (by x and y values) to the given Tile
     * @param y
     * @param x
     * @param toSetTo
     */
    public void setTile(int y,int x,Tile toSetTo){
        THE_GAME_BOARD[y][x] = toSetTo;
    }
}
