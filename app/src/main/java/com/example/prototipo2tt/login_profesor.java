package com.example.prototipo2tt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_profesor extends Activity {

    Button jbnporfesoringresar;
    Intent itn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_profesor);
        jbnporfesoringresar = (Button) findViewById(R.id.xbnprofesoringresar);

        jbnporfesoringresar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                itn = new Intent(login_profesor.this, profesor_home.class);
                startActivity(itn);
            }
        });
    }

}
