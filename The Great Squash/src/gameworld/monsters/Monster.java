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
    
    private void makeFromFile(Scanner scanFile) {
        super.setType(scanFile.nextLine());
        super.setSprite(scanFile.nextLine().charAt(0));
    }
}
