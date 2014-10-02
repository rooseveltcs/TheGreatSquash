package LAN;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private Users[] USERS;
    private boolean shouldRun = true;
    private String[] ips;

    public TestServer(int connections) {
        USERS = new Users[connections];
        ips = new String[connections];
        System.out.println("Starting server...");
        try {
            serverSocket = new ServerSocket(4180);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Server started...");
        for (int currentConnection = 0; currentConnection < Users.CONNECTIONS; currentConnection++) {
            try {
                socket = serverSocket.accept();
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                Users temp = new Users(out, in, USERS, connections);
                USERS[currentConnection] = temp;
                ips[currentConnection] = socket.getInetAddress().toString();
                System.out.println("Connection from: " + socket.getInetAddress());
                System.out.println("User is " + USERS[currentConnection]);
                Thread thread = new Thread(USERS[currentConnection]);
                thread.start();
                shouldRun = false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Users implements Runnable {

    public static int CONNECTIONS;
    DataOutputStream STREAM_OUT;
    DataInputStream STREAM_IN;
    Users[] USERS = new Users[CONNECTIONS];

    public Users(DataOutputStream out, DataInputStream in, Users[] users, int connections) {
        STREAM_OUT = out;
        STREAM_IN = in;
        USERS = users;
        CONNECTIONS = connections;
    }

    public void run() {
        while (!false) {
            try {
                String message = STREAM_IN.readUTF();
                for (int currentUser = 0; currentUser < CONNECTIONS; currentUser++) {
                    if (USERS[currentUser] != null) {
                        USERS[currentUser].STREAM_OUT.writeUTF(message);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
//how to do threads
//http://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
//https://www.youtube.com/watch?v=_1ThWf9Fkfo I'm att 8:10 in the video
