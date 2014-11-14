/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.tests;

import LAN.TestBroadcastClient;
import LAN.TestBroadcastServer;

/**
 *
 * @author ros_aljacobson001
 */
public class TestBroadcast {
    public static final boolean shouldServer = true;
    public static TestBroadcastServer SERVER;
    public static TestBroadcastClient CLIENT;
    public static void main(String[] args){
        if(shouldServer){
            CreateServer createServer = new CreateServer(SERVER);
            Thread createServerThread = new Thread(createServer);
            createServerThread.start();
        }
        CLIENT = new TestBroadcastClient();
        Thread clientThread = new Thread(CLIENT);
        clientThread.run();
    }
}
class CreateServer implements Runnable{
    TestBroadcastServer SERVER;
    public CreateServer(TestBroadcastServer server){
        SERVER = server;
    }
    @Override
    public void run() {
        SERVER = new TestBroadcastServer();
    }
    
}