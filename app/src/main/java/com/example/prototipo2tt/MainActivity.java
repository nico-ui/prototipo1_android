package com.example.prototipo2tt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button jbtn_alu, jbtn_pro;
    Intent itn, itn2;
    Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jbtn_alu = (Button) findViewById(R.id.xbtn_alumno);
        jbtn_pro = (Button) findViewById(R.id.button_profesor);
        toolb = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolb);

        jbtn_alu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                itn = new Intent(MainActivity.this, login.class);
                startActivity(itn);
            }
        });

        jbtn_pro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                itn2 = new Intent(MainActivity.this, login_profesor.class);
                startActivity(itn2);
            }
        });

    }
}