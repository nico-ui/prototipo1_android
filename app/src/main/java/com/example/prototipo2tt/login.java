package com.example.prototipo2tt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class login extends AppCompatActivity {

    Button jbtn_reg, jbtn_ing;
    EditText jet_bol, jet_pas;
    Intent itn, itn2;
    Toolbar toolb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_alumno);
        jbtn_reg = (Button) findViewById(R.id.btn_registrar);
        jbtn_ing = (Button) findViewById(R.id.btn_ingresar);
        jet_bol = (EditText) findViewById(R.id.txtboletaloginalumno);
        jet_pas = (EditText) findViewById(R.id.txtcontrasenaloginalumno);

        toolb = (Toolbar) findViewById(R.id.toolbarloginalumno);
        setSupportActionBar(toolb);

        //BOTÓN REGISTRAR
        jbtn_reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                itn = new Intent(login.this, registrar_alumno.class);
                startActivity(itn);
            }
        });

        //BOTÓN INGRESAR
        jbtn_ing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //OBLIGADO A TRABAJAR CON HILOS
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        final String res = enviarPost(jet_bol.getText().toString(), jet_pas.getText().toString());//si la cantidad de registro es uno cambiar de ventana;final para que res sea reconocido por todos los metodos
                        runOnUiThread(new Runnable() {//trabajar con la interfaz grafica dentro de un hilo
                            @Override
                            public void run() {
                                int r = objJSON(res);
                                if (r > 0) {
                                    itn2 = new Intent(login.this, alumno_home.class);
                                    startActivity(itn2);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Usuario incorrecto, intenta de nuevo",
                                            Toast.LENGTH_SHORT).show();
                                    jet_bol.setText("");
                                    jet_pas.setText("");
                                }
                            }
                        });
                    }
                };
                tr.start();
            }
        });
    }

    public String enviarPost(String bol, String pas) {//consumir datos
        String parametros = "boleta=" + bol + "&contrasena=" + pas;
        HttpURLConnection conection = null;
        String respuesta = "";//variable almacena respuesta
        try {
            URL url = new URL("http://192.168.1.66/php/validacion.php");
            conection = (HttpURLConnection) url.openConnection();
            conection.setRequestMethod("POST");//indicar metodo de envio
            conection.setRequestProperty("Content-Length", "" + Integer.toString(parametros.getBytes().length));
            conection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conection.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();

            Scanner inStream = new Scanner(conection.getInputStream());
            while (inStream.hasNextLine())//recorrer todas la lineas que devuelve el web service
                respuesta += (inStream.nextLine());
        } catch (Exception e) {

        }
        return respuesta.toString();
    }

    public int objJSON(String rspta) {//contar cuantos registro hay en la respuesta
        int res = 0;
        try {
            JSONArray json = new JSONArray(rspta);//conviertir nuevamente a JSON
            if (json.length() > 0)
                res = 1;//usuario correcto retorna 1
        } catch (Exception e) {
        }
        return res;
    }

}
