package com.dfj.baiduclubchat.Util;

import com.dfj.baiduclubchat.common.User;
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

    public static User resolveUser(String res){
        String ss[] = res.split("_");
        User user = new User();
        user.setAccount(Integer.parseInt(ss[0]));
        user.setNick(ss[1]);
        user.setTrends(ss[3]);
//        user.setSex(ss[4]);
//        user.setAge(Integer.parseInt(ss[5]));
//        user.setLev(Integer.parseInt(ss[6]));
        return user;
    }
}
