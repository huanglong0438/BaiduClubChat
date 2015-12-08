package com.dfj.baiduclubchat;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dfj.baiduclubchat.Util.Resolver;
import com.dfj.baiduclubchat.common.ClubMessage;
import com.dfj.baiduclubchat.common.ClubMessageType;
import com.dfj.baiduclubchat.common.User;
import com.dfj.baiduclubchat.entity.FriendInfo;
import com.dfj.baiduclubchat.model.ManageClientConServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static com.dfj.baiduclubchat.Util.Resolver.resolveFriendsList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    public static String res = "";
    private ListView listView;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getActivity().findViewById(R.id.lv_friends);
        IntentFilter filter = new IntentFilter("com.dfj.baiduclubchat.retfriends");
        getActivity().registerReceiver(friendListReceiver,filter);
    }

    private BroadcastReceiver friendListReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String res = intent.getExtras().getString("res");
            ArrayList<FriendInfo> friends = Resolver.resolveFriendsList(res);
            FriendsAdapter adapter = new FriendsAdapter(getActivity(),friends);
            listView.setAdapter(adapter);
        }
    };


    private ArrayList<String> getFriends(){
        ArrayList<String> friends = new ArrayList<>();
        friends.add("张三");
        friends.add("李四");
        friends.add("路人甲");
        friends.add("蒙奇·D·路飞");
        return friends;
    }
}
