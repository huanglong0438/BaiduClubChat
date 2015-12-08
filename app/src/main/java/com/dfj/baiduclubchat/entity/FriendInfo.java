package com.dfj.baiduclubchat.entity;

/**
 * Created by DC on 2015/12/7.
 */
public class FriendInfo {
    private int account;
    private String nickname;
    private String trends;
    private int isOnline;
    private static FriendInfo friendInfo;

    public static FriendInfo singleFriendInfo(){
        if (friendInfo == null){
            return new FriendInfo();
        }
        return friendInfo;
    }

    public void setAccount(int account){
        this.account = account;
    }
    public int getAccount(){
        return account;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getNickname(){
        return nickname;
    }
    public void setTrends(String trends){
        this.trends = trends;
    }
    public String getTrends(){
        return trends;
    }
    public void setIsOnline(int isOnline){
        this.isOnline = isOnline;
    }
    public int getIsOnline(){
        return isOnline;
    }

}
