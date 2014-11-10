/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.tests;

import GUI.GameGUI;
import gameworld.Inventory;

/**
 *
 * @author ros_Dmlamarca
 */
public class GUITestMain {
    public static void main(String[] args) {
        //GameGUI gui = new GameGUI();
        Inventory inventory = new Inventory(2, 3, 4, 5);
        System.out.println(inventory);
    }
}
