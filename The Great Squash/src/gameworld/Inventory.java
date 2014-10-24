/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import items.Armour;
import items.Consumable;
import items.SpellBook;
import items.Weapon;
import java.util.ArrayList;

/**
 *
 * @author ros_dmlamarca
 */
public class Inventory {
    ArrayList<Consumable> CONSUMABLES;
    int CONSUMABLES_SIZE_LIMIT;
    ArrayList<SpellBook> SPELLBOOKS;
    int SPELLBOOKS_SIZE_LIMIT;
    ArrayList<Armour> ARMOURS;
    int ARMOURS_SIZE_LIMIT;
    ArrayList<Weapon> WEAPONS;
    int WEAPONS_SIZE_LIMIT;
    
    public Inventory(int weaponSize, int armourSize, int consumableSize, int spellbookSize) {
        CONSUMABLES = new ArrayList<Consumable>();
        SPELLBOOKS = new ArrayList<SpellBook>();
        ARMOURS = new ArrayList<Armour>();
        WEAPONS = new ArrayList<Weapon>();
        
        CONSUMABLES_SIZE_LIMIT = consumableSize;
        SPELLBOOKS_SIZE_LIMIT = spellbookSize;
        ARMOURS_SIZE_LIMIT = armourSize;
        WEAPONS_SIZE_LIMIT = weaponSize;
    }
    
    public void addWeapon(Weapon weapon) {
        if(WEAPONS.size() < WEAPONS_SIZE_LIMIT) {
            WEAPONS.add(weapon);
        }
    }
}
