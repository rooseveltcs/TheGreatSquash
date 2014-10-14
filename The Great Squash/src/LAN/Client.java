/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LAN;

import gameworld.Board;
import gameworld.Creature;
import gameworld.Player;
import gameworld.Tile;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
public class Client {

    private DataInputStream STREAM_IN;
    private DataOutputStream STREAM_OUT;
    private Socket SOCKET;
    private DataInputStream CHAT_IN;
    private DataOutputStream CHAT_OUT;
    private Socket CHAT_SOCKET;
    private Board MY_BOARD;

    public Client(String ip, int portNumber, Board myBoard) {
        MY_BOARD = myBoard;
        Thread connectToServerThread = new Thread();
    }

    public Client(String ip, int portNumber) {
        ConnectToServerThread connectThread = new ConnectToServerThread(this, ip, portNumber);
        Thread connectToServerThread = new Thread(connectThread);
        connectToServerThread.start();
    }

    public void connectToServer(String ip, int portNumber) {
        System.out.println("Connecting to server at " + ip);
        try {
            SOCKET = new Socket(ip, portNumber);
            System.out.println("Connection successful.");
            STREAM_IN = new DataInputStream(SOCKET.getInputStream());
            STREAM_OUT = new DataOutputStream(SOCKET.getOutputStream());
            ServerDataHandler serverInput = new ServerDataHandler(STREAM_IN, this);
            Thread serverDataThread = new Thread(serverInput);
            serverDataThread.start();
        } catch (UnknownHostException ex) {
            System.out.println("Sorry but that ip adress was not found.");
        } catch (IOException ex) {
            System.out.println("Sorry but we could not connect to the server with that port.");
        }
        System.out.println("Connecting to chat server.");
        try {
            CHAT_SOCKET = new Socket(ip, portNumber++);
            System.out.println("Connection successful.");
            CHAT_IN = new DataInputStream(CHAT_SOCKET.getInputStream());
            CHAT_OUT = new DataOutputStream(CHAT_SOCKET.getOutputStream());
            ChatInput chatHandler = new ChatInput(CHAT_IN, CHAT_OUT, this);
            Thread chatThread = new Thread(chatHandler);
            chatThread.start();
        } catch (UnknownHostException ex) {
            System.out.println("Sorry but that ip address was not found for the chat server.");
        } catch (IOException ex) {
            System.out.println("Sorry but a chat server was not found on that port number.");
        }

    }

    public Board getBoard() {
        return MY_BOARD;
    }
}

class ServerDataHandler implements Runnable {

    private DataInputStream STREAM_IN;
    private DataOutputStream STREAM_OUT;
    private Client MY_CLIENT;

    public ServerDataHandler(DataInputStream in, Client myClient) {
        STREAM_IN = in;
        MY_CLIENT = myClient;
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
        System.out.println("Need to implement interpretServerData()");
        Scanner messageScanner = new Scanner(serverData);
        String theCommand = messageScanner.next();
        if (theCommand.equals(CommandHolder.CREATURE)) {
            int newY = messageScanner.nextInt();
            int newX = messageScanner.nextInt();
            char character = (char) messageScanner.nextInt();
            Player temp = new Player(character, MY_CLIENT.getBoard(), newY, newX);
            MY_CLIENT.getBoard().setTileCreature(newX, newX, temp);
        }

    }

    public void sendMove(int y, int x) {
        String toSend = CommandHolder.MOVING + " " + y + " " + x;
        try {
            STREAM_OUT.writeUTF(toSend);
        } catch (IOException ex) {
            System.out.println("Failed to send the movement command to the server, please check you connection.");
        }
    }
}

class ChatInput implements Runnable {

    DataInputStream STREAM_IN;
    DataOutputStream STREAM_OUT;
    Client CLIENT;

    public ChatInput(DataInputStream in, DataOutputStream out, Client client) {
        STREAM_IN = in;
        STREAM_OUT = out;
        CLIENT = client;
    }

    @Override
    public void run() {
        while (!false) {
            try {
                try {
                    String message = STREAM_IN.readUTF();
                    //this is where the client would send the message to the GUI to display
                } catch (SocketException se) {
                    System.out.println("Connection to chat server lost.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendChatMessage(String toSend) {
        try {
            STREAM_OUT.writeUTF(toSend);
        } catch (IOException ex) {
            System.out.println("Failure to send chat message.");
        }
    }
}

class ConnectToServerThread implements Runnable {

    Client CLIENT;
    String IP = "";
    int PORT_NUMBER;

    public ConnectToServerThread(Client client) {
        CLIENT = client;
        String IP = "10.135.66.52";
        PORT_NUMBER = 45005;
    }

    public ConnectToServerThread(Client client, String ip, int portNumber) {
        CLIENT = client;
        IP = ip;
        PORT_NUMBER = portNumber;
    }

    @Override
    public void run() {
        CLIENT.connectToServer(IP, PORT_NUMBER);
    }
}
