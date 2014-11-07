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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_aljacobson001
 */
public class BroadCastClient implements Runnable {

    private Client CLIENT;
    private Socket BROADCAST_SOCKET;
    private DataOutputStream STREAM_OUT;
    private DataInputStream STREAM_IN;
    
    public BroadCastClient(Client client){
        CLIENT = client;
        try {
            BROADCAST_SOCKET = new Socket("255.255.255.255",CommandHolder.BROADCAST_PORT_NUMBER);
            STREAM_OUT = new DataOutputStream(BROADCAST_SOCKET.getOutputStream());
            STREAM_IN = new DataInputStream(BROADCAST_SOCKET.getInputStream());
        } catch (UnknownHostException ex) {
            System.out.println("BroadCastClient: Unable to connect to the broadcast ip, check your network connection");
        } catch (IOException ex) {
            System.out.println("BroadCastClient: Unable to connect to the broadcast address through " + CommandHolder.BROADCAST_PORT_NUMBER);
        }
    }
    @Override
    public void run() {
        while(CommandHolder.ANSWER_TO_LIFE_THE_UNIVERSE_AND_EVERYTHING == 42){
            try {
                String input = STREAM_IN.readUTF();
            } catch (IOException ex) {
                System.out.println("BroadCastClient: Unable to listen to the broadcast address.");
            }
        }
    }
    
    public void interpretInput(String in){
        
    }
    
    public void findServers(){
        try {
            STREAM_OUT.writeUTF(CommandHolder.CLIENT_BROADCAST_MESSAGE);
        } catch (IOException ex) {
            System.out.println("BroadCastClient: Unable to send a message to the broadcast address");
        }
    }
}
