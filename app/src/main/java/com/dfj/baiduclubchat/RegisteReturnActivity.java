package com.dfj.baiduclubchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by fyy on 2015/12/7.
 */
public class RegisteReturnActivity extends Activity {

    private TextView registeAccount;
    private Button loginNowBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe_return);
        registeAccount = (TextView) findViewById(R.id.registe_account);
        Bundle bundle=getIntent().getExtras();
        int account=bundle.getInt("NewAccount");
        registeAccount.setText(""+account);
        loginNowBtn = (Button) findViewById(R.id.login_now_btn);

        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisteReturnActivity.this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
