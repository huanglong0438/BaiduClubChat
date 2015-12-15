package com.dfj.baiduclubchat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dfj.baiduclubchat.entity.Msg;

import java.util.List;

/**
 * Created by fyy on 2015/12/7.
 */
public class DialogAdapter extends BaseAdapter {

    private Context context;
    private List<Msg> list;
    LayoutInflater inflater;

    public DialogAdapter(Context context,List<Msg> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.dialog_list_item,null);

        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
        TextView dialogTitle = (TextView) convertView.findViewById(R.id.dialog_title);
        TextView dialogContent = (TextView) convertView.findViewById(R.id.dialog_content);

        Msg re = list.get(position);
        imageView1.setImageResource(R.drawable.ic_profile);
        dialogTitle.setText(re.getName());
        dialogContent.setText(re.getContent());
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
