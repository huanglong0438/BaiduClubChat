package com.dfj.baiduclubchat;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dfj.baiduclubchat.Util.Resolver;
import com.dfj.baiduclubchat.common.ClubMessage;
import com.dfj.baiduclubchat.common.ClubMessageType;
import com.dfj.baiduclubchat.common.User;
import com.dfj.baiduclubchat.entity.Msg;
import com.dfj.baiduclubchat.model.ClientConServerThread;
import com.dfj.baiduclubchat.model.ManageClientConServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fyy on 2015/12/1.
 */
public class ChatActivity extends Activity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter msgAdapter;
    private List<Msg> msgList = new ArrayList<Msg>();
    private User user;
    private int faccount;
    private String fnick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //user = (User) getIntent().getSerializableExtra("user");
        user = Resolver.resolveUser(MainActivity.myInfo);
        faccount = getIntent().getIntExtra("faccount",0);
        fnick = getIntent().getStringExtra("fnick");
        initMsgs();
        msgAdapter = new MsgAdapter(ChatActivity.this,R.layout.msg_item,msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(msgAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    new SendMsgThread(content,faccount).start();  //先试试发给自己
                    Msg msg = new Msg("我",content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    msgAdapter.notifyDataSetChanged();;
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");
                }else{
                    Toast.makeText(ChatActivity.this,"输入内容为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("com.dfj.bcc.mes");
        MyBroadcastReceiver br = new MyBroadcastReceiver();
        registerReceiver(br, myIntentFilter);

    }

    private void initMsgs(){
        Msg msg1 = new Msg("Tom","Hello ~",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("我","你是谁？",Msg.TYPE_SEND);
        msgList.add(msg2);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String[] msgContent = intent.getStringArrayExtra("message");
            Msg msg = new Msg(msgContent[3],msgContent[5],Msg.TYPE_RECEIVED);
            msgList.add(msg);
            msgAdapter.notifyDataSetChanged();
            msgListView.setSelection(msgList.size());
        }
    }

    class SendMsgThread extends Thread{
        private String content;
        private int receiverAccount;
        public SendMsgThread(String content,int receiverAccount){
            this.content = content;
            this.receiverAccount = receiverAccount;
        }
        public void run(){
            try {
                ClubMessage cm = new ClubMessage();
                cm.setType(ClubMessageType.COM_MES);
                cm.setSender(user.getAccount());
                cm.setSenderNick(user.getNick());
                cm.setReceiver(receiverAccount);
                cm.setContent(content);
                ObjectOutputStream oos = ManageClientConServer.getObjectOutputStream(user.getAccount());
                oos.writeObject(cm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
