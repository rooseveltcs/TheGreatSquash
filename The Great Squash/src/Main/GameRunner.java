package Main;

import LAN.Client;
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
        
    }
}
