package LAN;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TestServer {
    static ServerSocket serverSocket;
    static Socket socket;
    static DataOutputStream out;
    
    public static void main(String[] args) throws IOException{
        System.out.println("Starting server...");
        serverSocket = new ServerSocket(7777);
        System.out.println("ServerStarted...");
        socket = serverSocket.accept();
        out = new DataOutputStream(socket.getOutputStream());
        Scanner console = new Scanner(System.in);
        while(true){
            out.writeUTF(console.nextLine());
        }
    }
}
