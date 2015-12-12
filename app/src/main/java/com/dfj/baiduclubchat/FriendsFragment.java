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
import android.widget.AdapterView;
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
    ArrayList<FriendInfo> friends = null;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int faccount = friends.get(position).getAccount();
                String fnick = friends.get(position).getNickname();
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                intent.putExtra("user",FriendsFragment.this.getActivity().getIntent().getSerializableExtra("user"));
                intent.putExtra("faccount",faccount);
                getActivity().startActivity(intent);
            }
        });
    }

    private BroadcastReceiver friendListReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String res = intent.getExtras().getString("res");
            friends = Resolver.resolveFriendsList(res);
            FriendsAdapter adapter = new FriendsAdapter(getActivity(),friends);
            listView.setAdapter(adapter);
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("com.dfj.baiduclubchat.retfriends");
        getActivity().registerReceiver(friendListReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(friendListReceiver);
    }
}
