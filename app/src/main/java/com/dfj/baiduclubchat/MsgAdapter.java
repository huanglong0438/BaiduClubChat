package com.dfj.baiduclubchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by fyy on 2015/12/1.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {

    private int resourceId;

    public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftName = (TextView) view.findViewById(R.id.left_name);
            viewHolder.rightName = (TextView) view.findViewById(R.id.right_name);
            viewHolder.leftMsgLayout = (LinearLayout) view.findViewById(R.id.left_msg_layout);
            viewHolder.rightMsgLayout = (LinearLayout) view.findViewById(R.id.right_msg_layout);
            viewHolder.leftMessage = (TextView) view.findViewById(R.id.left_message);
            viewHolder.rightMessage = (TextView) view.findViewById(R.id.right_message);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(msg.getType() == Msg.TYPE_RECEIVED){
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftName.setText(msg.getName());
            viewHolder.leftMessage.setText(msg.getContent());
        }else if(msg.getType() == Msg.TYPE_SEND){
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightName.setText(msg.getName());
            viewHolder.rightMessage.setText(msg.getContent());
        }
        return view;
    }

    class ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftName;
        TextView rightName;
        LinearLayout leftMsgLayout;
        LinearLayout rightMsgLayout;
        TextView leftMessage;
        TextView rightMessage;
    }
}