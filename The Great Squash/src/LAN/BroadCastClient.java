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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_aljacobson001
 */
public class BroadCastClient implements Runnable {
    private Socket BROADCAST_SOCKET;
    private DataOutputStream STREAM_OUT;
    private DataInputStream STREAM_IN;
    private ArrayList<String> IPS;
    private ArrayList<String> SERVER_NAMES;
    private ArrayList<String> PLAYER_COUNTS;
    
    public BroadCastClient(){
        try {
            BROADCAST_SOCKET = new Socket("255.255.255.255",CommandHolder.BROADCAST_PORT_NUMBER);
            STREAM_OUT = new DataOutputStream(BROADCAST_SOCKET.getOutputStream());
            STREAM_IN = new DataInputStream(BROADCAST_SOCKET.getInputStream());
            findServers();
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
                interpretInput(input);
            } catch (IOException ex) {
                System.out.println("BroadCastClient: Unable to listen to the broadcast address.");
            }
        }
    }
    
    public void interpretInput(String in){
        Scanner inScanner = new Scanner(in);
        if(in.startsWith(CommandHolder.SERVER_BROADCAST_MESSAGE)){
            inScanner.next();
            String ip = inScanner.next();
            IPS.add(ip);
            String serverName = inScanner.next();
            SERVER_NAMES.add(serverName);
            String playerCount = inScanner.next();
            PLAYER_COUNTS.add(playerCount);
        }
        
    }
    
    public void findServers(){
        try {
            STREAM_OUT.writeUTF(CommandHolder.CLIENT_BROADCAST_MESSAGE);
        } catch (IOException ex) {
            System.out.println("BroadCastClient: Unable to send a message to the broadcast address");
        }
    }
    
    public String getInfo(int serverNumber){
        return  IPS.get(serverNumber) + "   " + SERVER_NAMES.get(serverNumber) + "   " + PLAYER_COUNTS.get(serverNumber);
    }
}
