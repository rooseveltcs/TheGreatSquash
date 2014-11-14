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
        CLIENT = new TestBroadcastClient();
        Thread clientThread = new Thread(CLIENT);
        clientThread.run();
        if(shouldServer){
            SERVER = new TestBroadcastServer();
        }
    }
}
