package com.dfj.baiduclubchat.model;

/**
 * Created by DC on 2015/12/4.
 */
import com.dfj.baiduclubchat.common.User;

import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ManageClientConServer {
    private static HashMap hm=new HashMap<Integer,ClientConServerThread>();
    private static HashMap hm2 = new HashMap<Integer,ObjectOutputStream>();
    private static HashMap hm3 = new HashMap<Integer,User>();
    //把创建好的ClientConServerThread放入到hm
    public static void addClientConServerThread(int account,ClientConServerThread ccst){
        hm.put(account, ccst);
    }

    //可以通过account取得该线程
    public static ClientConServerThread getClientConServerThread(int i){
        return (ClientConServerThread)hm.get(i);
    }

    public static void addObjectOutputStream(int account,ObjectOutputStream oos){
        hm2.put(account,oos);
    }
    public static ObjectOutputStream getObjectOutputStream(int i){
        return (ObjectOutputStream) hm2.get(i);
    }

    public static void addUser(int account,User user){
        hm3.put(account,user);
    }
    public static User getUser(int account){
        return (User) hm3.get(account);
    }
}
