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
            String objectCode = "";
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                if (!line.equals("")) {
                    if (objectCode.equals("")) {
                        objectCode += line;
                    } else {
                        objectCode += "\n" + line;
                        System.out.println(line);
                    }
                } else {
                    break;
                }
            }
            
            System.out.println(objectCode);
            Hashtable<String, Displayable> creatorTable = getCreatorTable(objectCode);
            
            System.out.println(creatorTable.get("#"));
            System.out.println(creatorTable.get("+"));
            
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry bub, but we couldn't make your file (well File Scanner). It just wasn't in the numbers.");
        }

        System.out.println();
        return null;
    }

    private static Hashtable<String, Displayable> getCreatorTable(String input) {
        Hashtable<String, Displayable> hashTable = new Hashtable<String, Displayable>();
        Scanner inputScanner = new Scanner(input);

        while (inputScanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(inputScanner.nextLine());

            String key = lineScanner.next();
            String objectName = lineScanner.next();

            Displayable object = createDisplayable(objectName);

            hashTable.put(key, object);
        }

        return hashTable;
    }

    private static Displayable createDisplayable(String objectName) {
        Displayable object = null;

        if (objectName.equals("wall")) {
            object = new Wall();
        } else if (objectName.equals("closeddoor")) {
            object = new Door(true);
        } else if (objectName.equals("opendoor")) {
            object = new Door(false);
        }

        return object;
    }
    
    private static Board makeBoard(Hashtable<String, Displayable> creatorTable) {
        
        return null;
    }
}
