package com.example.prototipo2tt;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class registrar_alumno extends AppCompatActivity {
    Button jbn1;
    EditText jet1, jet2, jet3, jet4, jet5, jet6;
    Toolbar toolb;

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
        toolb = (Toolbar) findViewById(R.id.toolbarregistraralumno);
        setSupportActionBar(toolb);

    }

    public void registrar(View view) {
        /*ejecutarservicio("http://192.168.1.68:80" +
                "/prototipo_1_tt/insertar.php");*/

        String nombre = jet1.getText().toString();
        String paterno = jet2.getText().toString();
        String materno = jet3.getText().toString();
        String email = jet4.getText().toString();
        String boleta = jet5.getText().toString();
        String contrasena = jet6.getText().toString();
        new DescargarImagen(registrar_alumno.this).execute(nombre, paterno, materno, email, boleta, contrasena);

    }
    /*
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
    */
}

class DescargarImagen extends AsyncTask<String, Void, String>{
    private WeakReference<Context> context;

    public DescargarImagen(Context context){
        this.context = new WeakReference<>(context);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected String doInBackground(String... params){
        String registrar_url = "http://192.168.1.66:80/prototipo_1_tt/conexion.php";
        String resultado = null;

        try{
            URL url = new URL(registrar_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8) );

            String nombre = params[0];
            String apPaterno = params[1];
            String apMaterno = params[2];
            String email = params[3];
            String boleta = params[4];
            String contrasena = params[5];

            String data = URLEncoder.encode("nombre", "UTF-8")+"="+URLEncoder.encode(nombre, "UTF-8")
                    +"&"+ URLEncoder.encode("apPaterno", "UTF-8")+"="+URLEncoder.encode(apPaterno, "UTF-8")
                    +"&"+ URLEncoder.encode("apMaterno", "UTF-8")+"="+URLEncoder.encode(apMaterno, "UTF-8")
                    +"&"+ URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")
                    +"&"+ URLEncoder.encode("boleta", "UTF-8")+"="+URLEncoder.encode(boleta, "UTF-8")
                    +"&"+ URLEncoder.encode("contrasena", "UTF-8")+"="+URLEncoder.encode(contrasena, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }

            resultado = stringBuilder.toString();

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();



        } catch (MalformedURLException e) {
            Log.d("MiAPP", "se ha utilizado una url con formato incorrecto");
            resultado = "Se ha producido un error";
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("MiAPP", "error de red");
            resultado = "Se ha producido un error con tu conexion a internet";
        }
        return resultado;
    }

    protected void onPostExecute(String resultado){
        Toast.makeText(context.get(), resultado, Toast.LENGTH_SHORT).show();
    }

}
