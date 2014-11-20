package Main;

import LAN.Client;
import LAN.CommandHolder;
import LAN.Server;
import gameworld.Board;

public class GameRunner {
    public static Board GAME_BOARD;
    public static Client CLIENT;
    public static Server SERVER;
    public static boolean shouldServer = true;
    
    public static void main(String[] args) {
        if(shouldServer){
            SERVER = new Server(10);
        }
        
//        //connect to the server with the client
//        String serverAddress = CommandHolder.AARON_WORK_IP;
//        int serverPort = CommandHolder.COMMAND_PORT_NUMBER;
//        CLIENT = new Client(serverAddress,serverPort);
        GAME_BOARD = CLIENT.getBoard();
    }
}
