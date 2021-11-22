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

public class login_profesor extends AppCompatActivity {

    EditText editTextNumEmpleado, editTextPasswordEncargado;
    Button btnLoginEncargado, btnRegisterEncargado, btnForgetPasswordEncargado;
    Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_profesor);
        bindUi();

        toolb = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolb);

        btnLoginEncargado.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String numEmpleado = editTextNumEmpleado.getText().toString();
                String password = editTextPasswordEncargado.getText().toString();
                if (loginEncargado(numEmpleado, password)){
                    goToHomeProfesor();
                }
            }
        });

        btnRegisterEncargado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(login_profesor.this, registrar_encargado.class);
                startActivity(intent2);
            }
        });
    }

    private void bindUi(){
        btnLoginEncargado = (Button) findViewById(R.id.btnLoginEncargado);
        btnRegisterEncargado = (Button) findViewById(R.id.btnRegisterEncargado);
        btnForgetPasswordEncargado = (Button) findViewById(R.id.btnForgetPasswordEncargado);
        editTextNumEmpleado = (EditText) findViewById(R.id.editTextNumEmpleado);
        editTextPasswordEncargado = (EditText) findViewById(R.id.editTextPasswordEncargado);

    }

    private boolean loginEncargado(String numEmpleado, String password){
        if (!isValidNumEncargado(numEmpleado)){
            Toast.makeText(this,"El numero de Empleado es invalido, Intente de nuevo", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)){
            Toast.makeText(this,"La contraseña es invalida, Intente de nuevo", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidNumEncargado(String numEmpleado){
        if (numEmpleado.length() > 0 && numEmpleado.length() >= 10){
            return true;
        } else {
            return false;
        }
    }
    private boolean isValidPassword(String password){

        return password.length() >= 4;
    }

    private void goToHomeProfesor(){
        Intent intent1 = new Intent(this, profesor_home.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent1);
    }

}
