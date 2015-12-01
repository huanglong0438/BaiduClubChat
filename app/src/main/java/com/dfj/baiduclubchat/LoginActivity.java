package com.dfj.baiduclubchat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by fyy on 2015/11/30.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private Button LoginBtn;
    private Button RegisterBtn;
    private EditText AccountInput;
    private EditText PasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginBtn = (Button) findViewById(R.id.login_btn);
        RegisterBtn = (Button) findViewById(R.id.register_btn);
        AccountInput = (EditText) findViewById(R.id.account);
        PasswordInput = (EditText) findViewById(R.id.password);
        LoginBtn.setOnClickListener(this);
        RegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login_btn:
                String account = AccountInput.getText().toString();
                String password = PasswordInput.getText().toString();
                login(account,password);
                break;
            case R.id.register_btn:
                Intent intent_register = new Intent();
                intent_register.setClass(this,RegisterActivity.class);
                startActivity(intent_register);
                break;
            default:
                break;
        }
    }

    public void login(String account,String password){
        Intent intent_login = new Intent(this,MainActivity.class);
        startActivity(intent_login);
    }
}
