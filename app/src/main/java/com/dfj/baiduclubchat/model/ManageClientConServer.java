package com.dfj.baiduclubchat.model;

/**
 * Created by DC on 2015/12/4.
 */
import java.net.Socket;
import java.util.HashMap;

public class ManageClientConServer {
    private static HashMap hm=new HashMap<Integer,ClientConServerThread>();
    private static HashMap hm2 = new HashMap<Integer,Socket>();
    //把创建好的ClientConServerThread放入到hm
    public static void addClientConServerThread(int account,ClientConServerThread ccst){
        hm.put(account, ccst);
    }

    //可以通过account取得该线程
    public static ClientConServerThread getClientConServerThread(int i){
        return (ClientConServerThread)hm.get(i);
    }

    public static void addSocket(int account,Socket socket){
        hm2.put(account,socket);
    }
    public static Socket getSocket(int i){
        return (Socket) hm2.get(i);
    }
}
