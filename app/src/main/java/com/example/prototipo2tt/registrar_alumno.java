package com.example.prototipo2tt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class registrar_alumno extends Activity {
    Button jbn1;
    EditText jet1, jet2, jet3, jet4, jet5, jet6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_alumno);
        jbn1 = (Button) findViewById(R.id.btn_registrar);
        jet1 = (EditText) findViewById(R.id.editTextTextPersonName3);
        jet2 = (EditText) findViewById(R.id.editTextTextPersonName4);
        jet3 = (EditText) findViewById(R.id.editTextTextPersonName5);
        jet4 = (EditText) findViewById(R.id.editTextTextEmailAddress);
        jet5 = (EditText) findViewById(R.id.editTextNumber2);
        jet6 = (EditText) findViewById(R.id.editTextTextPassword3);

    }

    public void registrar(View view) {
        ejecutarservicio("http://192.168.1.68:80" +
                "/prototipo_1_tt/insertar.php");
    }

    private void ejecutarservicio(String url) {
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Operación exitosa", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nombre", jet1.getText().toString());
                parametros.put("apPaterno", jet2.getText().toString());
                parametros.put("apMaterno", jet3.getText().toString());
                parametros.put("email", jet4.getText().toString());
                parametros.put("boleta", jet5.getText().toString());
                parametros.put("contraseña", jet6.getText().toString());
                return parametros;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(this);
        rq.add(sr);
    }
}

