package com.example.prototipo2tt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class login_profesor extends AppCompatActivity {

    Button jbnporfesoringresar;
    Intent itn;
    Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_profesor);
        jbnporfesoringresar = (Button) findViewById(R.id.btn_ingresar_profesor);
        toolb = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolb);

        jbnporfesoringresar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                itn = new Intent(login_profesor.this, profesor_home.class);
                startActivity(itn);
            }
        });
    }

}
