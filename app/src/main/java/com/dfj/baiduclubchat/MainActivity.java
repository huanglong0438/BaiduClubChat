package com.dfj.baiduclubchat;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dfj.baiduclubchat.entity.Msg;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener  {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    public static String myInfo;
    MyBroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.tab_chats, DialogueFragment.class)
                .add(R.string.tab_friends, FriendsFragment.class)
                .add(R.string.tab_groups, GroupFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);


        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup viewGroup, int i, PagerAdapter pagerAdapter) {
                ImageView imageView = new ImageView(viewPagerTab.getContext());;
                LinearLayout view = new LinearLayout(viewPagerTab.getContext());

                switch (i){
                    case 0:
                        imageView.setImageResource(R.drawable.ic_chat);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.ic_friend);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.ic_group);
                        break;
                }
                view.addView(imageView);
                view.setGravity(Gravity.CENTER);
                view.setLayoutParams(new LinearLayout.LayoutParams(-2,-1));
                return view;
            }
        });

        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction("com.dfj.bcc.mes");
        br = new MyBroadcastReceiver();
        registerReceiver(br, myIntentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(br);
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

    }

    @Override
    protected void onDestroy() {
        //下线
        super.onDestroy();
    }


    class MyBroadcastReceiver extends BroadcastReceiver {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {
            String[] msgContent = intent.getStringArrayExtra("message");
            String name = msgContent[3];
            String content = msgContent[5];
            int faccount = Integer.parseInt(msgContent[2]);
            Intent intentGoChat = new Intent(MainActivity.this,ChatActivity.class);
            intentGoChat.putExtra("user", MainActivity.this.getIntent().getSerializableExtra("user"));
            intentGoChat.putExtra("faccount",faccount);

            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intentGoChat, 0);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new Notification.Builder(MainActivity.this)
                    .setSmallIcon(R.drawable.ic_friend)
                    .setTicker("收到" + name +"的消息")
                    .setContentTitle(name)
                    .setContentText(content)
                    .setContentIntent(pendingIntent).setNumber(1).build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notification);
        }
    }
}
