package com.dfj.baiduclubchat.Util;

import com.dfj.baiduclubchat.entity.FriendInfo;

import java.util.ArrayList;

/**
 * Created by DC on 2015/12/7.
 */
public class Resolver {
    public static ArrayList<FriendInfo> resolveFriendsList(String res){
        String ss[] = res.split(" ");
        ArrayList<FriendInfo> friends = new ArrayList<>();
        FriendInfo friendInfo = FriendInfo.singleFriendInfo();
        for(String a: ss){
            if(a!=""){
                String b[]=a.split("_");
                friendInfo.setAccount(Integer.parseInt(b[0]));
                friendInfo.setNickname(b[1]);
                friendInfo.setTrends(b[3]);
                friendInfo.setIsOnline(Integer.parseInt(b[4]));
                friends.add(friendInfo);
            }
        }
        return friends;

    }
}
