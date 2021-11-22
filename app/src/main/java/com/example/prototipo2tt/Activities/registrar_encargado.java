package com.example.prototipo2tt.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prototipo2tt.R;

public class registrar_encargado extends AppCompatActivity {

    EditText editTextNumeroEncargado, editTextNombresEncargado, editTextFirsNameEncargado;
    EditText editTextSecondNameEncargado, editTextEmailEncargado, editTextPassEncargado;
    EditText editTextConfPassEncargado;
    Button buttonRegisterEncargado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_encargado);
        bindUI();
        Spinner spinner = (Spinner)findViewById(R.id.SpinnerLaboratorios);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_laboratorios, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),
                        "Selecionado: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_LONG).show();
                String laboratorio = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void bindUI(){
        editTextNumeroEncargado = (EditText) findViewById(R.id.editTextNumeroEncargado);
        editTextNombresEncargado = (EditText) findViewById(R.id.editTextNombresEncargado);
        editTextFirsNameEncargado = (EditText) findViewById(R.id.editTextFirstNameEncargado);
        editTextSecondNameEncargado = (EditText) findViewById(R.id.editTextSecondNameEncargado);
        editTextEmailEncargado = (EditText) findViewById(R.id.editTextEmailEncargado);
        editTextPassEncargado = (EditText) findViewById(R.id.editTextPassEncargado);
        editTextConfPassEncargado = (EditText) findViewById(R.id.editTextConfPassEncargado);
        buttonRegisterEncargado = (Button) findViewById(R.id.btnRegistrarEncargado);
    }

}