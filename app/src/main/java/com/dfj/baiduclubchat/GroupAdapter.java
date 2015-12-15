package com.dfj.baiduclubchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dfj.baiduclubchat.entity.Group;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by fyy on 2015/12/7.
 */
public class GroupAdapter extends BaseAdapter {

    private Context context;
    private List<Group> groupList;
    LayoutInflater inflater;

    public GroupAdapter(Context context,List<Group> groupList){
        this.context = context;
        this.groupList = groupList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.group_list_item,null);

        ImageView groupImage = (ImageView) convertView.findViewById(R.id.groupImage);
        TextView groupName = (TextView) convertView.findViewById(R.id.groupName);

        Group re = groupList.get(position);
        groupImage.setImageResource(R.drawable.ic_group);
        groupName.setText(re.getGroupName());
        return convertView;
    }

    @Override
    public int getCount() {
        return groupList.size();
    }

    @Override
    public Object getItem(int position) {
        return groupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
