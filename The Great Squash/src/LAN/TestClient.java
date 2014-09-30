package LAN;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {

    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;

    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server...");
        //Aaron's work station is 10.135.66.52
        //Dylan's work station is 10.135.65.230
        socket = new Socket("10.135.66.52",4180);
        System.out.println("Connection successful.");
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        Input streamIn = new Input(in);
        Thread thread = new Thread(streamIn);
        thread.start();
        Scanner sc = new Scanner(System.in);
        while(!false){
            String outMessage = sc.nextLine();
            out.writeUTF(outMessage);
        }
        
    }
}

class Input implements Runnable {

    DataInputStream STREAM_IN;

    public Input(DataInputStream in) {
        STREAM_IN = in;
    }

    public void run() {
        while (!false) {
            try {
                String message = STREAM_IN.readUTF();
                System.out.println(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}