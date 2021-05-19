package com.example.codisa;

import Utilidades.variables;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class login extends AppCompatActivity {
TextView txt_usuario,txt_pass;
    String mensaje,passwd,user="";

    ProgressDialog pdLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().setTitle("CODISA APP V.1.0");
        txt_usuario=(TextView)findViewById(R.id.txt_usuario);
        txt_pass=(TextView)findViewById(R.id.txt_pass);
     }


    public void login (View v){

        final AsyncCaller task = new AsyncCaller();
        task.execute();



     }

     private void verificar_login(){
         //  String user = "grupomaeharsa";
         // String passwd = "123456";
          user=  txt_usuario.getText().toString();
          passwd=txt_pass.getText().toString();
        String url = "jdbc:oracle:thin:@192.168.6.122:1521:XE";
        String driver = "oracle.jdbc.driver.OracleDriver";
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        try
        {
            if(user.length()==0){
               mensaje="INGRESE USUARIO";
            }
            else if(passwd.length()==0)
            {
                mensaje="INGRESE CONTRASEÑA";

            }
            else
            {
                Class.forName(driver);
                connection= DriverManager.getConnection(url,user ,passwd );

               // Toast.makeText(login.this,"REGISTRO EXITOSO",Toast.LENGTH_LONG).show();
                mensaje="1";
            }
        }
        catch (SQLException se)
        {
           // Log.e("error here 1 : ", se.getMessage());

            if(se.getErrorCode()==1017){
                mensaje="USUARIO O CONTRASEÑA INCORRECTA, FAVOR VERIFIQUE.";
             }
            else if (se.getErrorCode()==17002)
            {
                mensaje="ERROR DE CONEXION, VERIFIQUE LA RED.";
            }
            else if (se.getErrorCode()==20)
            {
                mensaje="ERROR DE CONEXION, VERIFIQUE LA RED.";
            }
            else {
                 mensaje=se.getMessage().toString();
            }

        }
        catch (ClassNotFoundException e)
        {
           // Log.e("error here 2 : ", e.getMessage());
        }

     }

    class AsyncCaller extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading = ProgressDialog.show(login.this, "VERIFICANDO",
                    "ESPERE...", true);

        }
        @Override
        protected Void doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here

            verificar_login();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

             if(mensaje=="1"){
                 Utilidades.variables.contenedor_menu="STKW002,STKW001";
                 variables.userdb=user;
                 variables.passdb=passwd;
                 Intent i=new Intent(login.this,MainActivity.class);
                 startActivity(i);
                 finish();
             }
             else {
                 new AlertDialog.Builder(login.this)
                         .setTitle("ATENCION!!!")
                         .setMessage(mensaje)
                         .setNegativeButton("CERRAR", null).show();
             }

             pdLoading.dismiss();
        }

    }

}