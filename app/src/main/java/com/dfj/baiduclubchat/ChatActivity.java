package com.dfj.baiduclubchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dfj.baiduclubchat.entity.Msg;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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

    }

    private void initMsgs(){
        Msg msg1 = new Msg("Tom","Hello ~",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("我","你是谁？",Msg.TYPE_SEND);
        msgList.add(msg2);
    }
}
