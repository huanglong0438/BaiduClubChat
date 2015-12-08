package com.dfj.baiduclubchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dfj.baiduclubchat.entity.FriendInfo;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by DC on 2015/12/7.
 */
public class FriendsAdapter extends BaseAdapter {
    Context context;
    ArrayList<FriendInfo> friendList;
    LayoutInflater layoutInflater;

    public FriendsAdapter(Context context,ArrayList<FriendInfo> friendList){
        this.context = context;
        this.friendList = friendList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        View view = convertView;
        FriendInfo friendInfo = friendList.get(position);

        if (view == null){
            view = layoutInflater.inflate(R.layout.friends_list_item,null);
            vh = new ViewHolder();
            vh.profile = (ImageView) view.findViewById(R.id.friends_list_profile);
            vh.nickname = (TextView) view.findViewById(R.id.friends_list_nickname);
            vh.trends = (TextView) view.findViewById(R.id.friends_list_trends);
            view.setTag(vh);
        }
        else{
            vh = (ViewHolder) view.getTag();
        }

        vh.profile.setImageResource(R.drawable.ic_profile);
        vh.nickname.setText(friendInfo.getNickname());
        vh.trends.setText(friendInfo.getTrends());
        return view;
    }

    public static class ViewHolder{
        ImageView profile;
        TextView nickname;
        TextView trends;
    }
}
