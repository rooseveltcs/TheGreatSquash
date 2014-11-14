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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_aljacobson001
 */
public class TestBroadcastClient implements Runnable{
    private DataInputStream STREAM_IN;
    private DataOutputStream STREAM_OUT;
    private Socket BROADCAST_SOCKET;
    public TestBroadcastClient(){
        try {
            BROADCAST_SOCKET = new Socket("255.255.255.255",CommandHolder.BROADCAST_PORT_NUMBER);
        } catch (UnknownHostException ex) {
            System.out.println("TestBroadcastClient: Can't connect to the broadcast address");
        } catch (IOException ex) {
            System.out.println("TestBroadcastClient: Can't connect to the broadcast port");
        }
    }

    @Override
    public void run() {
        try {
            STREAM_OUT.writeUTF(CommandHolder.CLIENT_BROADCAST_MESSAGE);
        } catch (IOException ex) {
            System.out.println("TestBroadcastClient: Cant send the broadcast message");
        }
        while(true){
            try {
                String in = STREAM_IN.readUTF();
                interpretIn(in);
            } catch (IOException ex) {
                System.out.println("TestBroadcastClient: Cant read from the stream in");
            }
        }
    }
    
    public void interpretIn(String in){
        Scanner inScanner = new Scanner(in);
        String theCommand = inScanner.next();
        if(theCommand.equals(CommandHolder.SERVER_BROADCAST_MESSAGE)){
            System.out.println("TestBroadcastClient: Message from the server: " + in);
        }
    }
}