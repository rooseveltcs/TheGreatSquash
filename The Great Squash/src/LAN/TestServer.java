package LAN;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestServer {

    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;
    static DataInputStream in;
    static Users[] USERS;

    public static void main(String[] args) throws IOException {
        System.out.println("Starting server...");
        serverSocket = new ServerSocket(4180);
        System.out.println("Server started...");
        while (!false) {
            socket = serverSocket.accept();
            for (int currentConnection = 0; currentConnection < Users.CONNECTIONS; currentConnection++) {
                System.out.println("Connection from: " + socket.getInetAddress());
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());
                if (USERS[currentConnection] == null) {//the error occurs here -it says that the connection was reset-
                    USERS[currentConnection] = new Users(out, in, USERS);
                    Thread thread = new Thread(USERS[currentConnection]);
                    thread.start();
                    break;
                }
            }
        }
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
