package com.dfj.baiduclubchat;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dfj.baiduclubchat.entity.Msg;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogueFragment extends Fragment {

    private List<Msg> dialogs = null;

    public DialogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialogue, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = (ListView) getActivity().findViewById(R.id.lv_dialog);
        dialogs = getMsgList();
        DialogAdapter adapter = new DialogAdapter(getActivity(),dialogs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                startActivity(intent);
            }
        });
    }


    private List<Msg> getMsgList(){
        List<Msg> dialogs = new ArrayList<Msg>();
        Msg msg = new Msg("我", "测试对话1", Msg.TYPE_SEND);
        Msg msg2 = new Msg("路人甲","测试对话2",Msg.TYPE_RECEIVED);
        dialogs.add(msg);
        dialogs.add(msg2);
        return dialogs;
    }
}
