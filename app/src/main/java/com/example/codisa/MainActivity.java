package com.example.codisa;

import Utilidades.variables;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public void onBackPressed()  {
        Utilidades.controles.volver_atras(this,this,login.class,"DESEA SALIR DE LA APLICACION?",3);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       String[] array_opciones=variables.contenedor_menu.split(",");

        for(int i=0; i<array_opciones.length; i++)
        {
            int ID_BOTONES = getResources().getIdentifier(array_opciones[i], "id", getPackageName());
            Button stock = ((Button) findViewById(ID_BOTONES));

            stock.setVisibility(View.VISIBLE);
        }

    }

    public void ir_registro(View v){
        Intent i=new Intent(this,registro_inventario.class);
        startActivity(i);
    }

    public void ir_stkw001(View v){
        Intent i=new Intent(this,test_con.class);
        startActivity(i);
    }
}