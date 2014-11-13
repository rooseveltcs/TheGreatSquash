/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LAN;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author ros_aljacobson001
 */
public class TestBroadcastServer {
    
    private Socket BROADCAST_SOCKET;
    private DataOutputStream STREAM_OUT;
    private DataInputStream STREAM_IN;
    private int CONNECTIONS = 10;
    
    public TestBroadcastServer(){
        try {
            BROADCAST_SOCKET = new Socket("255.255.255.255",CommandHolder.BROADCAST_PORT_NUMBER);
            STREAM_OUT = new DataOutputStream(BROADCAST_SOCKET.getOutputStream());
            STREAM_IN = new DataInputStream(BROADCAST_SOCKET.getInputStream());
        } catch (UnknownHostException ex) {
            System.out.println("TestBroadcastServer: Can't connect to the broadcast address.");
        } catch (IOException ex) {
            System.out.println("TestBroadcastServer: Can't connect to the broadcast address on that port");
        }
        while(42 == CommandHolder.ANSWER_TO_LIFE_THE_UNIVERSE_AND_EVERYTHING){
            try {
                String in = STREAM_IN.readUTF();
            } catch (IOException ex) {
                System.out.println("TestBroadcastServer: Lost connection to the broadcast address");
            }
        }
    }
    
    public void interpretInput(String in){
        Scanner inScanner = new Scanner(in);
        String command = inScanner.next();
        if(command.equals(CommandHolder.CLIENT_BROADCAST_MESSAGE)){
            try {
                STREAM_OUT.writeUTF(CommandHolder.SERVER_BROADCAST_MESSAGE + " yo ");
            } catch (IOException ex) {
                System.out.println("TestBroadcastServer: I can't send messages");
            }
        }
    }
}