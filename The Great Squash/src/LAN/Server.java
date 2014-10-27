/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LAN;

import GUI.LANMessanger;
import gameworld.Board;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
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
    private int PORT_NUMBER = 7777;
    private int CHAT_PORT_NUMBER = PORT_NUMBER + 1;
    private Board THE_BOARD;

    public Server(int connections, Board gameBoard) {
        IPS = new String[connections];
        SERVER_CLIENT_CONNECTIONS = new ServerClientConnection[connections];
        SERVER_CHAT_CONNECTIONS = new ServerClientChat[connections];
        System.out.println("Starting server...");
        //keeps creating the server on different ports until an unused one is found
        while (true) {
            try {
                SERVER_SOCKET = new ServerSocket(CommandHolder.COMMAND_PORT_NUMBER);
                //CHAT_SERVER_SOCKET = new ServerSocket(7778);
                break;
            } catch (IOException ex) {
//                CHAT_PORT_NUMBER++;
//                PORT_NUMBER++;
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
                SOCKET = SERVER_SOCKET.accept();
                DATA_OUT = new DataOutputStream(SOCKET.getOutputStream());
                DATA_IN = new DataInputStream(SOCKET.getInputStream());
                IPS[currentConnection] = SOCKET.getInetAddress().toString();
                System.out.println("Connection from " + IPS[currentConnection]);
                ServerClientConnection newConnect = new ServerClientConnection(DATA_IN, DATA_OUT, SERVER_CLIENT_CONNECTIONS, IPS, gameBoard, this);
                SERVER_CLIENT_CONNECTIONS[currentConnection] = newConnect;
                Thread CurrentConnection = new Thread(newConnect);
                CurrentConnection.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //once all clients have connected the server sends the message to load into the game
        startGame();
    }

    public Board getBoard() {
        return THE_BOARD;
    }

    public void startGame() {
    }
}

class ServerClientConnection implements Runnable {

    DataInputStream STREAM_IN;
    DataOutputStream STREAM_OUT;
    ServerClientConnection[] SERVER_CLIENT_CONNECTIONS;
    String[] IPS;
    Board GAME_BOARD;
    Server THE_SERVER;

    public ServerClientConnection(DataInputStream in, DataOutputStream out, ServerClientConnection[] serverClientConnections, String[] ips, Board gameBoard, Server server) {
        SERVER_CLIENT_CONNECTIONS = serverClientConnections;
        THE_SERVER = server;
        STREAM_IN = in;
        STREAM_OUT = out;
        IPS = ips;
    }

    @Override
    public void run() {
        while (!false) {
            try {
                String toSend = STREAM_IN.readUTF();
                System.out.println("Incoming message: " + toSend);
                interpretMessage(toSend);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void interpretMessage(String theMessage) {
        Scanner messageScanner = new Scanner(theMessage);
        String theCommand = messageScanner.next();
        if (theCommand.equals(CommandHolder.MOVE_CREATURE)) {
            int newY = messageScanner.nextInt();
            int newX = messageScanner.nextInt();
            String name = messageScanner.next();
            int oldY = messageScanner.nextInt();
            int oldX = messageScanner.nextInt();
            THE_SERVER.getBoard().setTileCreature(oldX, oldX, null);
            THE_SERVER.getBoard().setTileCreature(newY, newX, THE_SERVER.getBoard().getCreature(name));
        } else if (theCommand.equals(CommandHolder.INITIALIZE_CREATURES)) {
            sendCreatures();
        } else if (theCommand.equals(CommandHolder.INITIALIZE_OBSTACLES)) {
            sendObstacles();
        } else if (theCommand.equals(CommandHolder.INITIALIZE_FLOORS)) {
            sendFloors();
        }
    }

    public void sendFloors() {
    }

    public void sendObstacles() {
        String toSend = CommandHolder.THE_OBSTACLES + " " + THE_SERVER.getBoard().getObstacles().size();
        for (int currentObstacle = 0; currentObstacle < THE_SERVER.getBoard().getObstacles().size(); currentObstacle++) {
            toSend += THE_SERVER.getBoard().getObstacles().get(currentObstacle);
        }
        sendCommandOne(toSend);
    }

    public void sendCreatures() {
        String toSend = CommandHolder.THE_CREATURES + " " + THE_SERVER.getBoard().getCreatures().size();
        for (int currentCreature = 0; currentCreature < THE_SERVER.getBoard().getCreatures().size(); currentCreature++) {
            toSend += THE_SERVER.getBoard().getCreatures().get(currentCreature).toServerData();
        }
        sendCommandOne(toSend);
    }
    
    public void sendCommandOne(String toSend){
        try {
            STREAM_OUT.writeUTF(toSend);
        } catch (IOException ex) {
            System.out.println("Unable to connect to a client");
        }
    }

    public void sendCommand(String toSend) {
        for (int currentConnection = 0; currentConnection < SERVER_CLIENT_CONNECTIONS.length; currentConnection++) {
            try {
                try {
                    SERVER_CLIENT_CONNECTIONS[currentConnection].STREAM_OUT.writeUTF(toSend);
                } catch (IOException ex) {
                    System.out.println("A client has disconnected: " + IPS[currentConnection]);
                }
            } catch (NullPointerException ex) {
            }
        }
    }
}

class ServerClientChat implements Runnable {

    DataInputStream STREAM_IN;
    DataOutputStream STREAM_OUT;
    ServerClientChat[] SERVER_CHAT_CONNECTIONS;

    public ServerClientChat(DataInputStream in, DataOutputStream out, ServerClientChat[] serverChatConnections) {
        STREAM_IN = in;
        STREAM_OUT = out;
        SERVER_CHAT_CONNECTIONS = serverChatConnections;
    }

    @Override
    public void run() {
        while (!false) {
            try {
                String toSend = STREAM_IN.readUTF();
                System.out.println("Incoming Chat " + toSend);
                for (int currentConnection = 0; currentConnection < SERVER_CHAT_CONNECTIONS.length; currentConnection++) {
                    try {
                        SERVER_CHAT_CONNECTIONS[currentConnection].STREAM_OUT.writeUTF(toSend);
                    } catch (SocketException ex) {
                        System.out.println("A client has disonnected");
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
