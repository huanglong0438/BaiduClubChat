package com.dfj.baiduclubchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dfj.baiduclubchat.common.User;
import com.dfj.baiduclubchat.model.ClubClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

/**
 * Created by fyy on 2015/11/30.
 */
public class RegisterActivity extends Activity {

    private EditText RegisteName;
    private EditText RegistePass;
    private EditText RegistePassAgain;
    private Button RegisteNow;

    public MyHandler handler = new MyHandler(this);

    private static class MyHandler extends Handler {
        WeakReference<RegisterActivity> mActivityReference;
        MyHandler(RegisterActivity activity) {
            mActivityReference= new WeakReference<RegisterActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(mActivityReference.get(),"注册失败",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    int account = msg.getData().getInt("NewAccount");
                    Intent intent = new Intent(mActivityReference.get(),RegisteReturnActivity.class);
                    intent.putExtra("NewAccount",account);
                    mActivityReference.get().startActivity(intent);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegisteName = (EditText) findViewById(R.id.registe_name);
        RegistePass = (EditText) findViewById(R.id.registe_password);
        RegistePassAgain = (EditText) findViewById(R.id.registe_password_again);
        RegisteNow = (Button) findViewById(R.id.registe_now);


        RegisteNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = RegisteName.getText().toString();
                String pass = RegistePass.getText().toString();
                String passAgain = RegistePassAgain.getText().toString();
                if(name.equals("")){
                    Toast.makeText(RegisterActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }
                if (pass.equals("")||passAgain.equals("")){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }
                if(!pass.equals(passAgain)){
                    Toast.makeText(RegisterActivity.this,"两次输入的密码不一致，请重试",Toast.LENGTH_SHORT).show();
                }else {
                    registe(name,pass);
                }
            }
        });
    }
    void registe(String userName,String userPass){
        new RegisterThread(userName,userPass).start();
//        Intent intent = new Intent(this,RegisteReturnActivity.class);
//        intent.putExtra("NewAccount",233);
//        startActivity(intent);
    }
    class RegisterThread extends Thread{
        private String username;
        private String password;
        RegisterThread(String username,String password){
            this.username = username;
            this.password = password;
        }
        public void run(){
            User user=new User();
            user.setPassword(password);
            user.setNick(username);
            user.setTrends("该用户暂时没有动态");
            user.setSex("未知");
            user.setAvatar(4);
            user.setLev(0);
            user.setAge(0);
            user.setTime("侏罗纪时代");
            user.setOperation("register");
            int res=new ClubClient(RegisterActivity.this).sendRegisterInfo(user);
            if (res >= 0){
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putInt("NewAccount",res);
                msg.setData(b);
                msg.what = 1;
                handler.sendMessage(msg);
            }
            else{
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        }
    }
}

