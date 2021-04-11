package com.example.prototipo2tt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registrar_alumno extends AppCompatActivity {
    Button jbn1;
    EditText jet1, jet2, jet3, jet4, jet5, jet6, jet7;
    Toolbar toolb;
    Spinner se;
    String str_boleta, str_nombre, str_primerAp, str_segundoAp, str_carrera, str_email, str_contrasena;
    String url = "http://192.168.1.66/php/insertar.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_alumno);
        //Button
        jbn1 = (Button) findViewById(R.id.btn_registrar);
        //Edit Text
        jet1 = (EditText) findViewById(R.id.idboleta);
        jet2 = (EditText) findViewById(R.id.idnombre);
        jet3 = (EditText) findViewById(R.id.idprimerApe);
        jet4 = (EditText) findViewById(R.id.idsegundoApe);
        jet5 = (EditText) findViewById(R.id.idemail);
        jet6 = (EditText) findViewById(R.id.idcontra);
        jet7 = (EditText) findViewById(R.id.idconfirmarcontra);
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

    public void registrar(View view) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Por favor espera...");

        //AUXILIAR
        String boleta = jet1.getText().toString();
        String nombre = jet2.getText().toString();
        String paterno = jet3.getText().toString();
        String materno = jet4.getText().toString();
        String email = jet5.getText().toString();
        String contrasena = jet6.getText().toString();
        String confcontrasena = jet7.getText().toString();

        if (boleta.equals("") || boleta.length()<10) {
            Toast.makeText(this, "Ingresa boleta valida", Toast.LENGTH_SHORT).show();
            //jet1.setError("Este campo no puede ser menor a 10");
        } else if(nombre.equals("")){
            Toast.makeText(this, "Ingresa nombre", Toast.LENGTH_SHORT).show();
        } else if(email.equals("")){
            Toast.makeText(this, "Ingresa email", Toast.LENGTH_SHORT).show();
        } else if(paterno.equals("")){
            Toast.makeText(this, "Ingresa primer apellido", Toast.LENGTH_SHORT).show();
        } else if(materno.equals("")){
            Toast.makeText(this, "Ingresa segundo apellido", Toast.LENGTH_SHORT).show();
        } else if(email.equals("")){
            Toast.makeText(this, "Ingresa email", Toast.LENGTH_SHORT).show();
        } else if(contrasena != confcontrasena){///////////////////////////////////////////////////////////////////////////////////
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            jet6.setText("");
            jet7.setText("");
        }else {
            pd.show();
            str_boleta = jet1.getText().toString().trim();
            str_nombre = jet2.getText().toString().trim();
            str_primerAp = jet3.getText().toString().trim();
            str_segundoAp = jet4.getText().toString().trim();
            str_email = jet5.getText().toString().trim();
            str_contrasena = jet6.getText().toString().trim();

            StringRequest r = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                public void onResponse(String r) {
                    pd.dismiss();
                    jet1.setText("");
                    jet2.setText("");
                    jet3.setText("");
                    jet4.setText("");
                    jet5.setText("");
                    jet6.setText("");
                    jet7.setText("");

                    Toast.makeText(registrar_alumno.this, r, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError error) {
                    pd.dismiss();
                    Toast.makeText(registrar_alumno.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show(); //detectar errores
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    //REFERENCIANDO A LA DB
                    params.put("boleta", str_boleta);
                    params.put("nombre", str_nombre);
                    params.put("apPaterno", str_primerAp);
                    params.put("apMaterno", str_segundoAp);
                    params.put("carrera", str_carrera);
                    params.put("email", str_email);
                    params.put("contrasena", str_contrasena);

                    return params;
                }
            };

            RequestQueue rq = Volley.newRequestQueue(registrar_alumno.this);
            rq.add(r);
        }
    }

}