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
        Monster lolita = new Monster("Human");
        System.out.println(lolita.getType());
        System.out.println(lolita.getSprite());
    }
}
