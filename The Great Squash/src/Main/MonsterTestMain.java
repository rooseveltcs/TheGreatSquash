/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import gameworld.monsters.Monster;

/**
 *
 * @author ros_Dmlamarca
 */
public class MonsterTestMain {
    public static void main(String[] args) {
        Monster lolita = new Monster("tigon");
        System.out.println(lolita.getType());
        System.out.println(lolita.getSprite());
        System.out.println("Spd: " + lolita.getSpeedMod());
        System.out.println("End: " + lolita.getEnduranceMod());
        System.out.println("Str: " + lolita.getStrengthMod());
        System.out.println("Int: " + lolita.getIntelligenceMod());
        System.out.println("Dex: " + lolita.getDexterityMod());
    }
}
