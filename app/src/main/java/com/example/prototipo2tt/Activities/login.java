package com.example.prototipo2tt.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prototipo2tt.R;

public class login extends AppCompatActivity {

    Button btnRegister, btnLogin, btnForgetPassword;
    EditText editTextBoleta, editTextPassword;
    Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_alumno);
        bindUI();
        toolb = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolb);

        //BOTÓN REGISTRAR
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent2 = new Intent(login.this, registrar_alumno.class);
                startActivity(intent2);
            }
        });

        //BOTÓN INGRESAR
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String boleta = editTextBoleta.getText().toString();
                String password = editTextPassword.getText().toString();
                if (login(boleta, password)){
                    goToAlumnoHome();
                }

            }
        });
    }

    private void bindUI(){
        btnRegister = (Button) findViewById(R.id.btnRegisterAlumno);
        btnLogin = (Button) findViewById(R.id.btnLoginAlumno);
        btnForgetPassword = (Button) findViewById(R.id.btnForgetPassword);
        editTextBoleta = (EditText) findViewById(R.id.editTextBoleta);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

    }

    private boolean login(String boleta, String password){
        if (!isValidBoleta(boleta)){
            Toast.makeText(this, "Numero de boleta no es valida, Intenta de nuevo", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)){
            Toast.makeText(this, "La contraseña no es valida, Intenta de nuevo", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidBoleta(String boleta){
        if (boleta.length() > 0 && boleta.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }
    private boolean isValidPassword(String password){
        return password.length() >= 4;
    }

    private void goToAlumnoHome(){
        Intent intent1 = new Intent(login.this, alumno_home.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent1);
    }


}
