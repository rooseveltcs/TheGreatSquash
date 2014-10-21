
package LAN;
import gameworld.Creature;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;



public class ServerDataHandler implements Runnable {

    private DataInputStream STREAM_IN;
    private DataOutputStream STREAM_OUT;
    private Client MY_CLIENT;

    public ServerDataHandler(DataInputStream in,DataOutputStream out,Client myClient) {
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
       // System.out.println("Need to implement interpretServerData()");
        Scanner messageScanner = new Scanner(serverData);
        String theCommand = messageScanner.next();
        if (theCommand.equals(CommandHolder.CREATURE)) {
            int newY = messageScanner.nextInt();
            int newX = messageScanner.nextInt();
            String name = messageScanner.next();
            MY_CLIENT.getBoard().setTileCreature(newY, newX, MY_CLIENT.getBoard().getCreature(name));
            MY_CLIENT.getGUI().updateDisplay();
        }

    }

    public void sendMove(int y, int x,Creature theCreature) {
        String toSend = CommandHolder.MOVING + " " + y + " " + x + " " + theCreature.getName();
        try {
            STREAM_OUT.writeUTF(toSend);
        } catch (IOException ex) {
            System.out.println("Failed to send the movement command to the server, please check you connection.");
        }
    }
}