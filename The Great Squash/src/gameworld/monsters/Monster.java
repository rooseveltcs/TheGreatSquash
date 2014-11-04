package gameworld.monsters;

import gameworld.Board;
import gameworld.Creature;
import java.util.Scanner;

/**
 *
 * @author ros_Dmlamarca
 */
public class Monster extends Creature {

    public Monster(String type) {
        Scanner scanFile = new Scanner(getClass().getResourceAsStream((type.toLowerCase()) + ".txt"));
        makeFromFile(scanFile);
    }
    
     public Monster(String type, Board board, int x, int y) {
        Scanner scanFile = new Scanner(getClass().getResourceAsStream((type.toLowerCase()) + ".txt"));
        makeFromFile(scanFile);
        super.BOARD = board;
        
    }
    

    private void makeFromFile(Scanner scanFile) {
        super.setType(scanFile.nextLine());
        super.setSprite(scanFile.nextLine().charAt(0));
        super.setSpeedMod(generateStat(scanFile.nextLine()));
        super.setEnduranceMod(generateStat(scanFile.nextLine()));
        super.setStrengthMod(generateStat(scanFile.nextLine()));
        super.setIntelligenceMod(generateStat(scanFile.nextLine()));
        super.setDexterityMod(generateStat(scanFile.nextLine()));
    }
    
    private int generateStat(String line) {
        Scanner readLine = new Scanner(line);
        int lowEdge = readLine.nextInt();
        int highEdge = readLine.nextInt();
        int randomStat = (int)(Math.random() * (highEdge - lowEdge + 1) + lowEdge);
        return randomStat;
    }
}
