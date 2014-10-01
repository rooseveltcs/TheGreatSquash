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

    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;
    static DataInputStream in;
    static Users[] USERS;
    static boolean shouldRun = true;
    static String[] ips = new String[10];

    public static void main(String[] args) throws IOException {
        USERS = new Users[10];
        System.out.println("Starting server...");
        serverSocket = new ServerSocket(4180);
        System.out.println("Server started...");
        for (int currentConnection = 0; currentConnection < Users.CONNECTIONS; currentConnection++) {
            socket = serverSocket.accept();
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            Users temp = new Users(out, in, USERS);
            USERS[currentConnection] = temp;
            ips[currentConnection] = socket.getInetAddress().toString();
            System.out.println("Connection from: " + socket.getInetAddress());
            System.out.println("User is " + USERS[currentConnection]);
            Thread thread = new Thread(USERS[currentConnection]);
            thread.start();
            shouldRun = false;
        }
        System.in.read();
    }
}

class Users implements Runnable {

    public static final int CONNECTIONS = 10;
    DataOutputStream STREAM_OUT;
    DataInputStream STREAM_IN;
    Users[] USERS = new Users[CONNECTIONS];

    public Users(DataOutputStream out, DataInputStream in, Users[] users) {
        STREAM_OUT = out;
        STREAM_IN = in;
        USERS = users;
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
