package com.dfj.baiduclubchat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dfj.baiduclubchat.common.ClubMessage;
import com.dfj.baiduclubchat.common.ClubMessageType;
import com.dfj.baiduclubchat.common.User;
import com.dfj.baiduclubchat.model.ClubClient;
import com.dfj.baiduclubchat.model.ManageClientConServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

/**
 * Created by fyy on 2015/11/30.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private Button LoginBtn;
    private Button RegisterBtn;
    private EditText AccountInput;
    private EditText PasswordInput;

    public MyHandler handler = new MyHandler(this);
    public Handler handler2 = new Handler();

    private static class MyHandler extends Handler {
        WeakReference<LoginActivity> mActivityReference;

        MyHandler(LoginActivity activity) {
            mActivityReference= new WeakReference<LoginActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    Toast.makeText(mActivityReference.get(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(mActivityReference.get(),"登陆成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mActivityReference.get(),MainActivity.class);
                    intent.putExtra("user",msg.getData().getSerializable("user"));
                    mActivityReference.get().startActivity(intent);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginBtn = (Button) findViewById(R.id.login_btn);
        RegisterBtn = (Button) findViewById(R.id.register_btn);
        AccountInput = (EditText) findViewById(R.id.account);
        PasswordInput = (EditText) findViewById(R.id.password);
        LoginBtn.setOnClickListener(this);
        RegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_btn:
                String account = AccountInput.getText().toString();
                String password = PasswordInput.getText().toString();
                login(account,password);
                break;
            case R.id.register_btn:
                Intent intent_register = new Intent();
                intent_register.setClass(this,RegisterActivity.class);
                startActivity(intent_register);
                break;
            default:
                break;
        }
    }

    public void login(String account,String password){
        new LoginThread(account,password).start();
    }
    class LoginThread extends Thread{
        private String account;
        private String password;
        LoginThread(String account,String password){
            this.account = account;
            this.password = password;
        }
        public void run(){
            User user = new User();
            user.setAccount(Integer.parseInt(account));
            user.setPassword(password);
            user.setOperation("login");
            boolean b=new ClubClient(LoginActivity.this).sendLoginInfo(user);
            if(b){
                try {
                    //发送一个要求返回在线好友的请求的Message
                    ObjectOutputStream oos = new ObjectOutputStream	(ManageClientConServer.getClientConServerThread(user.getAccount()).getS().getOutputStream());
                    ManageClientConServer.addObjectOutputStream(user.getAccount(),oos);
                    ClubMessage m=new ClubMessage();
                    m.setType(ClubMessageType.GET_ONLINE_FRIENDS);
                    m.setSender(user.getAccount());
                    oos.writeObject(m);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what = 1;
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",user);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }else {
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }

        }
    }
}
