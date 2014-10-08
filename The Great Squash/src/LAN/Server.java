/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LAN;

import GUI.LANMessanger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_aljacobson001
 */
public class Server {

    private ServerSocket SERVER_SOCKET;
    private ServerSocket CHAT_SERVER_SOCKET;
    private Socket SOCKET;
    private Socket CHAT_SOCKET;
    private DataOutputStream CHAT_OUT;
    private DataInputStream CHAT_IN;
    private DataOutputStream DATA_OUT;
    private DataInputStream DATA_IN;
    private String[] IPS;
    private ServerClientConnection[] SERVER_CLIENT_CONNECTIONS;
    private ServerClientChat[] SERVER_CHAT_CONNECTIONS;
    private int PORT_NUMBER = 45005;
    private int CHAT_PORT_NUMBER = 45006;

    public Server(int connections) {
        IPS = new String[connections];
        SERVER_CLIENT_CONNECTIONS = new ServerClientConnection[connections];
        SERVER_CHAT_CONNECTIONS = new ServerClientChat[connections];
        System.out.println("Starting serber...");
        //keeps creating the server on different ports until an unused one is found
        while (true) {
            try {
                SERVER_SOCKET = new ServerSocket(PORT_NUMBER);
                CHAT_SERVER_SOCKET = new ServerSocket(CHAT_PORT_NUMBER);
                break;
            } catch (IOException ex) {
                CHAT_PORT_NUMBER++;
                PORT_NUMBER++;
            }
        }
        try {
            System.out.println("Created the server on port #" + PORT_NUMBER + " with the ip adress of " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            System.out.println("Could not get local host address.");
        }
        //waits for all the clients to connect
        for (int currentConnection = 0; currentConnection < connections; currentConnection++) {
            try {
                CHAT_SOCKET = CHAT_SERVER_SOCKET.accept();
                CHAT_OUT = new DataOutputStream(CHAT_SOCKET.getOutputStream());
                CHAT_IN = new DataInputStream(CHAT_SOCKET.getInputStream());
                ServerClientChat chatTemp = new ServerClientChat(CHAT_IN,CHAT_OUT,SERVER_CHAT_CONNECTIONS);
                SOCKET = SERVER_SOCKET.accept();
                DATA_OUT = new DataOutputStream(SOCKET.getOutputStream());
                DATA_IN = new DataInputStream(SOCKET.getInputStream());
                ServerClientConnection newConnect = new ServerClientConnection(DATA_IN, DATA_OUT, SERVER_CLIENT_CONNECTIONS);
                IPS[currentConnection] = SOCKET.getInetAddress().toString();
                System.out.println("Connection from " + IPS[currentConnection]);
                Thread CurrentConnection = new Thread(newConnect);
                CurrentConnection.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //once all clients have connected the server sends the message to load into the game
        startGame();
    }
    
    public void startGame(){
        
    }
}

class ServerClientConnection implements Runnable {

    DataInputStream STREAM_IN;
    DataOutputStream STREAM_OUT;
    ServerClientConnection[] SERVER_CLIENT_CONNECTIONS;

    public ServerClientConnection(DataInputStream in, DataOutputStream out, ServerClientConnection[] serverClientConnections) {
        SERVER_CLIENT_CONNECTIONS = serverClientConnections;
        STREAM_IN = in;
        STREAM_OUT = out;
    }

    @Override
    public void run() {
        while (!false) {
            try {
                String toSend = STREAM_IN.readUTF();
                System.out.println("Incoming message: " + toSend);
                for (int currentConnection = 0; currentConnection < SERVER_CLIENT_CONNECTIONS.length; currentConnection++) {
                    try {
                        SERVER_CLIENT_CONNECTIONS[currentConnection].STREAM_OUT.writeUTF(toSend);

                    } catch (SocketException ex) {
                        System.out.println("A client has disconnected.");
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
class ServerClientChat implements Runnable{

    DataInputStream STREAM_IN;
    DataOutputStream STREAM_OUT;
    ServerClientChat[] SERVER_CHAT_CONNECTIONS;
    
    public ServerClientChat(DataInputStream in,DataOutputStream out,ServerClientChat[] serverChatConnections){
        STREAM_IN = in;
        STREAM_OUT = out;
        SERVER_CHAT_CONNECTIONS = serverChatConnections;
    }
    @Override
    public void run() {
        while(!false){
            try {
                String toSend = STREAM_IN.readUTF();
                System.out.println("Incoming Chat " + toSend);
                //for(int currentConnection = 0;currentConnection < )
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}