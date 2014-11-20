/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_dmlamarca
 */
public class CreatureCounter {

    public static String getCount(String type) {
        String name = "";
        File file = new File("." + "\\build\\classes\\gameworld\\tools");
        File countlog = null;
        for (int i = 0; i < file.listFiles().length; i++) {
            if (file.listFiles()[i].getName().equals("countlog")) {
                countlog = file.listFiles()[i];
            }
        }


        FileWriter writeFile;
        try {
            writeFile = new FileWriter(countlog);
            writeFile.write("I have 7 testicles");
        } catch (IOException ex) {
            Logger.getLogger(CreatureCounter.class.getName()).log(Level.SEVERE, null, ex);
        }



        return name;
    }
//    public CreatureCounter() {
//        counterTable = new Hashtable<String,Integer>();
//    }
//    public String getCount(String creatureType) {
//        String name = "";
//        if(!counterTable.containsKey(creatureType)) {
//            counterTable.put(creatureType,0);
//        } else {
//            int count = counterTable.get(creatureType);
//            counterTable.remove(creatureType);
//            counterTable.put(creatureType,count + 1);
//        }
//        
//        int creatureCount = counterTable.get(creatureType);
//        if(((double)(creatureCount)) / 100 >= 1) {
//            name = creatureType + "_" + creatureCount;
//        } else if(((double)(creatureCount)) / 10 >= 1) {
//            name = creatureType + "_0" + creatureCount;
//        } else {
//            name = creatureType + "_00" + creatureCount;
//        }
//        return name;
//    }
}
