/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LAN;

import GUI.TestMovementGUI;
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
    private TestMovementGUI GUI;
    private ServerDataHandler DATA_HANDLER;

    public Client(String ip, int portNumber, Board myBoard,TestMovementGUI gui) {
        GUI = gui;
        MY_BOARD = myBoard;
        ConnectToServerThread connectThread = new ConnectToServerThread(this);
        Thread connectToServerThread = new Thread(connectThread);
        connectToServerThread.start();
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
            DATA_HANDLER = new ServerDataHandler(STREAM_IN,STREAM_OUT,this);
            Thread serverDataThread = new Thread(DATA_HANDLER);
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
    
    public TestMovementGUI getGUI(){
        return GUI;
    }
    
    public ServerDataHandler getHandler(){
        return DATA_HANDLER;
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
        PORT_NUMBER = 7778;
    }

    public ConnectToServerThread(Client client, String ip, int portNumber) {
        CLIENT = client;
        IP = ip;
        PORT_NUMBER = portNumber;
    }

    @Override
    public void run() {
        System.out.println(IP);
        CLIENT.connectToServer(IP, PORT_NUMBER);
    }
}
