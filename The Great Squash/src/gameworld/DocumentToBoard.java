/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_dmlamarca
 */
public class DocumentToBoard {
    public static Board createBoard(String filePath) {
        
        
        try {
            File textFile = new File(filePath);
            Scanner readFile = new Scanner(textFile);
            while(readFile.hasNextLine()) {
                String line = readFile.nextLine();
                if(!line.equals("")) {
                    System.out.println(line);
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry bub, but we couldn't make your file (well File Scanner). It just wasn't in the numbers.");
        }
        
        System.out.println();
        return null;
    }
    
    private static Hashtable<String,Displayable> getCreatorTable(String line) {
        Hashtable<String,Displayable> hashTable = new Hashtable<String,Displayable>();
        Scanner lineScanner = new Scanner(line);
        
        String key = lineScanner.next();
        String 
        
        return null;
    }
}
