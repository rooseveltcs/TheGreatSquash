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

/**
 *
 * @author ros_aljacobson001
 */
public class Client {
    
    private DataInputStream STREAM_IN;
    private DataOutputStream STREAM_OUT;
    private Socket SOCKET;
    
    public Client(String ip,int portNumber){
        connectToServer(ip,portNumber);
    }
    
    public void connectToServer(String ip,int portNumber){
        System.out.println("Connecting to server at " + ip);
        try {
            SOCKET = new Socket(ip,portNumber);
            System.out.println("Connection successful.");
            STREAM_IN = new DataInputStream(SOCKET.getInputStream());
            STREAM_OUT = new DataOutputStream(SOCKET.getOutputStream());
            ServerInput serverInput = new ServerInput(STREAM_IN,this);
            Thread serverDataThread = new Thread(serverInput);
            serverDataThread.start();
        } catch (UnknownHostException ex) {
            System.out.println("Sorry but that ip adress was not found.");
        } catch (IOException ex) {
            System.out.println("Sorry but we could not connect to the server with that port.");
        }
    }
}
class ServerInput implements Runnable{
    private DataInputStream STREAM_IN;
    private Client MY_CLIENT;

    public ServerInput(DataInputStream in,Client myClient){
        STREAM_IN = in;
        MY_CLIENT = myClient;
    }
    @Override
    public void run() {
        while(!false){
            try {
                String serverData = STREAM_IN.readUTF();
                interpretServerData(serverData);
            } catch (IOException ex) {
                System.out.println("Sorry but we lost connection to the server");
                break;
            }
        }
    }
    
    public void interpretServerData(String serverData){
        System.out.println("Need to implement interpretServerData()");
    }
}