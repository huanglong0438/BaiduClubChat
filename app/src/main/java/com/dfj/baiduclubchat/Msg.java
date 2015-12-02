package com.dfj.baiduclubchat;

/**
 * Created by fyy on 2015/12/1.
 */
public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;
    private String name;
    private String content;
    private int type;

    public Msg(String name,String content,int type){
        this.name = name;
        this.content = content;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public String getContent(){
        return content;
    }
    public int getType(){
        return type;
    }
}
