/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import LAN.TypeHolder;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_dmlamarca
 */
public class Player extends Creature {
    
    public Player(char sprite, Board board, int y, int x,String name) {
         super(sprite,board,y,x,name,TypeHolder.PLAYER);
    }
    
    public Player(String name){
        String fileDirectory = "src/gameworld/players/" + name + ".player";
        try {
            Scanner fileScanner = new Scanner(new File(fileDirectory));
            loadFromFile(fileScanner);
        } catch (FileNotFoundException ex) {
            System.out.println("Player: File not found: " + fileDirectory);
        }
    }
    
    public Player(String name,char sprite,int health,int level,int speed,int endurance,int strength,int intelligence,int dexterity){
        super(name,sprite,health,level,speed,endurance,strength,intelligence,dexterity);
    }
    
    public void loadFromFile(Scanner playerScanner){
        boolean statsLoaded = false;
        boolean equipmentLoaded = false;
        boolean inventoryLoaded = false;
        boolean spellsLoaded = false;
        while(playerScanner.hasNext()){
            if(!statsLoaded){
                loadStats(playerScanner);
            }else if(!equipmentLoaded){
                
            }else if(!inventoryLoaded){
                
            }else if(!spellsLoaded){
                //we need to implement spells
            }
        }
    }
    
    public void loadStats(Scanner playerScanner){
        playerScanner.next();
        String name = playerScanner.next();
        playerScanner.next();
        char symbol = playerScanner.next().charAt(0);
        playerScanner.next();
        int health = playerScanner.nextInt();
        playerScanner.next();
        int level = playerScanner.nextInt();
        playerScanner.next();
        int xp = playerScanner.nextInt();
        playerScanner.next();
        int movement = playerScanner.nextInt();
        playerScanner.next();
        int endurance = playerScanner.nextInt();
        playerScanner.next();
        int strength = playerScanner.nextInt();
        playerScanner.next();
        int intelligence = playerScanner.nextInt();
        playerScanner.next();
        int dexterity = playerScanner.nextInt();
    }
}
