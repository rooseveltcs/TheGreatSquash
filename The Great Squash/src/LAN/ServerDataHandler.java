package LAN;

import gameworld.Creature;
import gameworld.Displayable;
import gameworld.Obstacle;
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

    public ServerDataHandler(DataInputStream in, DataOutputStream out, Client myClient) {
        STREAM_IN = in;
        STREAM_OUT = out;
        MY_CLIENT = myClient;
        System.out.println(MY_CLIENT);
        System.out.println(STREAM_OUT);
        System.out.println(STREAM_IN);
    }

    @Override
    public void run() {
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
        if (theCommand.equals(CommandHolder.MOVE_CREATURE)) {
            int newY = messageScanner.nextInt();
            int newX = messageScanner.nextInt();
            String name = messageScanner.next();
            int oldY = messageScanner.nextInt();
            int oldX = messageScanner.nextInt();
            MY_CLIENT.getBoard().setTileCreature(oldY,oldX,null);
            MY_CLIENT.getBoard().setTileCreature(newY, newX, MY_CLIENT.getBoard().getCreature(name));
            MY_CLIENT.getGUI().updateDisplay();
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
        if(displayable instanceof Obstacle) {
            Obstacle obstacle = (Obstacle)(displayable);
            toSend = CommandHolder.MOVE_CREATURE + " " + obstacle.getY() + " " + obstacle.getX() + " " + obstacle.getLabel() + obstacle.getPassable();
        }
    }
}