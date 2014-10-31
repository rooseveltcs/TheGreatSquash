package LAN;

import gameworld.Creature;
import gameworld.Displayable;
import gameworld.Obstacle;
import gameworld.Player;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerDataHandler implements Runnable {

    private DataInputStream STREAM_IN;
    private DataOutputStream STREAM_OUT;
    private Client MY_CLIENT;
    private boolean hasFloors = false;
    private boolean hasObstacles = false;
    private boolean hasCreatures = false;

    public ServerDataHandler(DataInputStream in, DataOutputStream out, Client myClient) {
        STREAM_IN = in;
        STREAM_OUT = out;
        MY_CLIENT = myClient;
    }

    @Override
    public void run() {
        InitEverythingThread initStuff = new InitEverythingThread(this);
        Thread initBoardThread = new Thread(initStuff);
        initBoardThread.start();
        while (!false) {
            try {
                String serverData = STREAM_IN.readUTF();
                interpretServerData(serverData);
            } catch (IOException ex) {
                System.out.println("Sorry but we lost connection to the server");
                break;
            }
        }
    }

    public void interpretServerData(String serverData) {
        Scanner messageScanner = new Scanner(serverData);
        String theCommand = messageScanner.next();
        System.out.println("Client: Recieved the following command: " + theCommand + " what do?");
        if (theCommand.equals(CommandHolder.MOVE_CREATURE)) {
            int newY = messageScanner.nextInt();
            int newX = messageScanner.nextInt();
            String name = messageScanner.next();
            int oldY = messageScanner.nextInt();
            int oldX = messageScanner.nextInt();
            MY_CLIENT.getBoard().setTileCreature(oldY, oldX, null);
            MY_CLIENT.getBoard().setTileCreature(newY, newX, MY_CLIENT.getBoard().getCreature(name));
            MY_CLIENT.getBoard().updateDisplay();
        } else if (theCommand.equals(CommandHolder.THE_CREATURES)) {
            System.out.println("Client: Recieved the creatures.");
            int numberOfCreatures = messageScanner.nextInt();
            for (int currentCreature = 0; currentCreature < numberOfCreatures; currentCreature++) {
                messageScanner.next();
                String label = messageScanner.next();
                int newY = messageScanner.nextInt();
                int newX = messageScanner.nextInt();
                double health = messageScanner.nextInt();
                String type = messageScanner.next();
                char sprite = messageScanner.next().charAt(0);
                if (type.equals(TypeHolder.PLAYER)) {
                    Player john = new Player(sprite, MY_CLIENT.getBoard(), newY, newX, label);
                    MY_CLIENT.getBoard().getCreatures().add(john);
                }
                //this is where other types of creatures go
            }
            System.out.println("Client: The creatures have been initialized");
            hasCreatures = true;
            MY_CLIENT.getBoard().setShouldPlayer(true);
        } else if (theCommand.equals(CommandHolder.THE_OBSTACLES)) {
            System.out.println("Client: Recieved the obstacles");
            int numberOfObstacles = messageScanner.nextInt();
            for (int currentObject = 0; currentObject < numberOfObstacles; currentObject++) {
                messageScanner.next();
                String label = messageScanner.next();
                int newY = messageScanner.nextInt();
                int newX = messageScanner.nextInt();
                boolean passable = messageScanner.nextBoolean();
                double health = messageScanner.nextDouble();
                char sprite = messageScanner.next().charAt(0);
                System.out.println("TODO implement types of obstacles");
            }
            System.out.println("Client: Obstacles have been initialized");
            hasObstacles = true;
        } else if (theCommand.equals(CommandHolder.THE_FLOORS)) {
        }
    }

    public void sendMove(int newY, int newX, int oldY, int oldX, Creature theCreature) {
        System.out.println(theCreature);
        String toSend = CommandHolder.MOVE_CREATURE + " " + newY + " " + newX + " " + theCreature.getName() + " " + oldY + " " + oldX;
        try {
            STREAM_OUT.writeUTF(toSend);
        } catch (IOException ex) {
            System.out.println("Failed to send the movement command to the server, please check you connection.");
        }
    }

    public void sendLocation(Displayable displayable) {
        String toSend = "";
        if (displayable instanceof Obstacle) {
            Obstacle obstacle = (Obstacle) (displayable);
            toSend = CommandHolder.MOVE_CREATURE + " " + obstacle.getY() + " " + obstacle.getX() + " " + obstacle.getLabel() + obstacle.getPassable();
        }
    }

    public void initEverything() {
        try {
            //STREAM_OUT.writeUTF(CommandHolder.INITIALIZE_FLOORS);
            STREAM_OUT.writeUTF(CommandHolder.INITIALIZE_OBSTACLES);
            while (!hasObstacles) {
            }
            STREAM_OUT.writeUTF(CommandHolder.INITIALIZE_CREATURES);
            while (!hasCreatures) {
            }
        } catch (IOException ex) {
            System.out.println("Unable to communicate with the server");
        }
    }
}

class InitEverythingThread implements Runnable{

    private ServerDataHandler HANDLER;
    
    public InitEverythingThread(ServerDataHandler handler){
        HANDLER = handler;
    }
    @Override
    public void run() {
        HANDLER.initEverything();
    }
    
}