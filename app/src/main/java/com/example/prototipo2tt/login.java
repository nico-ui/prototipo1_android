package com.example.prototipo2tt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends Activity {

    Button jbtn_reg, jbtn_ing;
    Intent itn, itn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_alumno);
        jbtn_reg = (Button) findViewById(R.id.btn_registrar);
        jbtn_ing = (Button) findViewById(R.id.btn_ingresar);

        jbtn_reg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                itn = new Intent(login.this, registrar_alumno.class);
                startActivity(itn);
            }
        });


        jbtn_ing.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                itn2 = new Intent(login.this, alumno_home.class);
                startActivity(itn2);
            }
        });


    }

}
