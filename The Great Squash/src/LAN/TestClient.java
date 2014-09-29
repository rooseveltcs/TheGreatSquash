package LAN;

import java.io.DataInputStream;
import java.net.Socket;

public class TestClient {
	static Socket socket;
	static DataInputStream in;

	public static void main(String[] args) throws Exception {
		System.out.println("Connecting...");
		socket = new Socket("10.135.66.52",7777);
		System.out.println("Connection successful");
		in = new DataInputStream(socket.getInputStream());
		while(true) {
			String test = in.readUTF();
			System.out.println("Message from server: " + test);
		}
	}
}
