/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import java.util.Hashtable;

/**
 *
 * @author ros_dmlamarca
 */
public class CreatureCounter {
    Hashtable<String, Integer> counterTable;
    
    public CreatureCounter() {
        counterTable = new Hashtable<String,Integer>();
    }
    
    public String getCount(String creatureType) {
        String name = "";
        if(!counterTable.containsKey(creatureType)) {
            counterTable.put(creatureType,0);
        } else {
            int count = counterTable.get(creatureType);
            counterTable.remove(creatureType);
            counterTable.put(creatureType,count + 1);
        }
        
        int creatureCount = counterTable.get(creatureType);
        if(((double)(creatureCount)) / 100 >= 1) {
            name = creatureType + "_" + creatureCount;
        } else if(((double)(creatureCount)) / 10 >= 1) {
            name = creatureType + "_0" + creatureCount;
        } else {
            name = creatureType + "_00" + creatureCount;
        }
        return name;
    }
}
