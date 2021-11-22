package com.example.prototipo2tt.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prototipo2tt.R;

public class registrar_alumno extends AppCompatActivity {
    Button buttonRegister;
    EditText editTextNumBoleta, editTextNombres, editTextFirstName, editTextSecondName;
    EditText editTextEmailAlumno, editTextPasswordAlumno, editTextConfPasswordAlumno;
    Toolbar toolb;
    Spinner se;
    String str_boleta, str_nombre, str_primerAp, str_segundoAp, str_carrera, str_email, str_contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_alumno);
        bindUI();
        //Toolbar
        toolb = (Toolbar) findViewById(R.id.toolbarregistraralumno);
        setSupportActionBar(toolb);
        //Spinner
        se = (Spinner) findViewById(R.id.idspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combo_especialidad, android.R.layout.simple_spinner_item);
        se.setAdapter(adapter);
        se.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(),
                        "Selecionado: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_LONG).show();
                str_carrera = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void bindUI(){
        //Button
        buttonRegister = (Button) findViewById(R.id.btnRegistrar);
        //Edit Text
        editTextNumBoleta = (EditText) findViewById(R.id.editTextNumBoleta);
        editTextNombres = (EditText) findViewById(R.id.editTextNombres);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextSecondName = (EditText) findViewById(R.id.editTextSecondName);
        editTextEmailAlumno = (EditText) findViewById(R.id.editTextEmailAlumno);
        editTextPasswordAlumno = (EditText) findViewById(R.id.editTextPass);
        editTextConfPasswordAlumno = (EditText) findViewById(R.id.editTextConfPassword);
    }

}