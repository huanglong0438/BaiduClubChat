package com.dfj.baiduclubchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by fyy on 2015/11/30.
 */
public class RegisterActivity extends Activity {

    private EditText RegisteName;
    private EditText RegistePass;
    private EditText RegistePassAgain;
    private Button RegisteNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegisteName = (EditText) findViewById(R.id.registe_name);
        RegistePass = (EditText) findViewById(R.id.registe_password);
        RegistePassAgain = (EditText) findViewById(R.id.registe_password_again);
        RegisteNow = (Button) findViewById(R.id.registe_now);


        RegisteNow.setOnClickListener(new View.OnClickListener() {
            String name = RegisteName.getText().toString();
            String pass = RegistePass.getText().toString();
            String passAgain = RegistePassAgain.getText().toString();
            @Override
            public void onClick(View v) {
                if(!pass.equals(passAgain)){
                    Toast.makeText(RegisterActivity.this,"",Toast.LENGTH_SHORT).show();
                    return ;
                }else {
                    registe(name,pass);
                }
            }
        });
    }
    void registe(String userName,String userPass){

    }
}

