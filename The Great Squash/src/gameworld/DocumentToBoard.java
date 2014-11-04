/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameworld;

import gameworld.monsters.Monster;
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
        Board board = null;
        try {
            File textFile = new File(filePath);
            Scanner readFile = new Scanner(textFile);

            String objectCode = getNextFileElement(readFile);
            String stringBoard = getNextFileElement(readFile);
            String creatureCode = getNextFileElement(readFile); 
            
            makeMonster(creatureCode);
            
            Hashtable<String, Displayable> creatorTable = getCreatorTable(objectCode);

            board = makeBoard(creatorTable, stringBoard);
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("Sorry bub, but we couldn't make your file (well File Scanner). It just wasn't in the numbers.");
        }

        System.out.println();
        return board;
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
            object = new Door(false);
        } else if (objectName.equals("opendoor")) {
            object = new Door(true);
        }

        return object;
    }

    private static String getNextFileElement(Scanner readFile) {
        String string = "";
        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if (!line.equals("")) {
                if (string.equals("")) {
                    string += line;
                } else {
                    string += "\n" + line;
                }
            } else {
                break;
            }
        }
        return string;
    }

    private static Board makeBoard(Hashtable<String, Displayable> creatorTable, String stringBoard) {
        Scanner getSize = new Scanner(stringBoard);
        int sizeX = 0;
        int sizeY = 0;
        while (getSize.hasNextLine()) {
            String line = getSize.nextLine();
            int lineX = line.length();
            if (lineX > sizeX) {
                sizeX = lineX;
            }
            sizeY++;
        }

        Board board = new Board(sizeY, sizeX);
        board.setBoardTilesNull();
        //System.out.println(sizeX + "|" + sizeY);
        Scanner getBoard = new Scanner(stringBoard);
        int y = 0;
        while (getBoard.hasNextLine()) {
            String line = getBoard.nextLine();
            for (int x = 0; x < line.length(); x++) {
                String currentChar = line.charAt(x) + "";
                Displayable displayable = creatorTable.get(currentChar);
                if(displayable != null) {
                    if(displayable instanceof Wall) {
                        Wall wall = ((Wall)(displayable)).clone();
                        //System.out.println(wall);
                        board.setTileObstacle(x, y,wall);
                    } else if(displayable instanceof Door) {
                        Door door = ((Door)(displayable)).clone();
                        //System.out.println(door);
                        board.setTileObstacle(x, y,door);
                    }
                }
            }
            //System.out.println();
            y++;
        }
        
        return board;
    }
    
    private static Monster makeMonster(String line) {
        Scanner readLine = new Scanner(line);
        String type = readLine.next();
        int y = readLine.nextInt();
        int x = readLine.nextInt();
        System.out.println(type);
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        return null;
    }
}
