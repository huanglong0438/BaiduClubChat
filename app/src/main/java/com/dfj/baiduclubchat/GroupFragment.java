package com.dfj.baiduclubchat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dfj.baiduclubchat.entity.Group;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {

    private List<Group> groups = null;

    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView listView = (ListView) getActivity().findViewById(R.id.lv_groups);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_expandable_list_item_1,getGroups());
        groups = getGroups();
        GroupAdapter adapter = new GroupAdapter(getActivity(),groups);
        listView.setAdapter(adapter);
    }

//    private ArrayList<String> getGroups(){
//        ArrayList<String> friends = new ArrayList<>();
//        friends.add("≤‚ ‘»∫1");
//        friends.add("≤‚ ‘»∫2");
//        friends.add("≤‚ ‘»∫3");
//        friends.add("≤‚ ‘»∫4");
//        return friends;
//    }

    private List<Group> getGroups(){
        List<Group> groups = new ArrayList<>();
        groups.add(new Group("≤‚ ‘»∫1"));
        groups.add(new Group("≤‚ ‘»∫2"));
        groups.add(new Group("≤‚ ‘»∫3"));
        return groups;
    }

}
