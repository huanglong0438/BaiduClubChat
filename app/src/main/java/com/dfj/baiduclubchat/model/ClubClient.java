package com.dfj.baiduclubchat.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dfj.baiduclubchat.LoginActivity;
import com.dfj.baiduclubchat.MainActivity;
import com.dfj.baiduclubchat.common.ClubMessage;
import com.dfj.baiduclubchat.common.ClubMessageType;
import com.dfj.baiduclubchat.common.User;
import com.dfj.baiduclubchat.entity.FriendInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by DC on 2015/12/4.
 */
public class ClubClient {
    private Context context;
    private static final String ip = "10.184.92.196";
    public Socket s;
    public ClubClient(Context context){
        this.context = context;
    }
    public boolean sendLoginInfo(Object obj){
        boolean b=false;
        try {
            s=new Socket();
            try{
                s.connect(new InetSocketAddress(ip,9999),2000);
            }catch(SocketTimeoutException e){
                //连接服务器超时
                final String exception = e.getMessage();
                ((LoginActivity) context).handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context,exception,Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(obj);
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            ClubMessage ms=(ClubMessage)ois.readObject();
            if(ms.getType().equals(ClubMessageType.SUCCESS)){
                //个人信息
                MainActivity.myInfo=ms.getContent();
                //创建一个该账号和服务器保持连接的线程
                ClientConServerThread ccst=new ClientConServerThread(context,s);
                //启动该通信线程
                ccst.start();
                //加入到管理类中
                ManageClientConServer.addClientConServerThread(((User)obj).getAccount(), ccst);
                b=true;
            }else if(ms.getType().equals(ClubMessageType.FAIL)){
                b=false;
            }
        } catch (IOException | ClassNotFoundException e) {
            final String exception = e.getMessage();
            ((LoginActivity) context).handler2.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context,exception,Toast.LENGTH_SHORT).show();
                }
            });
            e.printStackTrace();
        }
        return b;
    }

    public int sendRegisterInfo(Object obj){
        int b=-1;
        try {
            s=new Socket();
            try{
                s.connect(new InetSocketAddress(ip,9999),2000);
            }catch(SocketTimeoutException e){
                //连接服务器超时
                return -1;
            }
            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(obj);
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
            ClubMessage ms=(ClubMessage)ois.readObject();
            if(ms.getType().equals(ClubMessageType.SUCCESS)){
                b=ms.getReceiver();
            }else if(ms.getType().equals(ClubMessageType.FAIL)){
                b=-1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }



}
