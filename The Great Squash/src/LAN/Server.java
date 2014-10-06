/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LAN;

import GUI.LANMessanger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ros_aljacobson001
 */
public class Server {

    private ServerSocket SERVERSOCKET;
    private Socket SOCKET;
    private DataOutputStream OUT;
    private DataInputStream IN;
    private ArrayList<String> IPS;
    private ArrayList<ServerClientConnections> SERVER_CLIENT_CONNECTIONS;

    public Server() {
        IPS = new ArrayList<String>();
        SERVER_CLIENT_CONNECTIONS = new ArrayList<ServerClientConnections>();
    }
}

class ServerClientConnections implements Runnable {

    DataInputStream STREAM_IN;
    DataOutputStream STREAM_OUT;
    ArrayList<ServerClientConnections> SERVER_CLIENT_CONNECTIONS;

    public ServerClientConnections(DataInputStream in, DataOutputStream out, ArrayList serverClientConnections) {
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
                for (int currentConnection = 0; currentConnection < SERVER_CLIENT_CONNECTIONS.size(); currentConnection++) {
                    try {
                        SERVER_CLIENT_CONNECTIONS.get(currentConnection).STREAM_OUT.writeUTF(toSend);
                    }catch(SocketException se){
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