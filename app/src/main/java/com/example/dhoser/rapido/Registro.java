package com.example.dhoser.rapido;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    Intent volver;
    BaseDatos bd = new BaseDatos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void acciones (View v){
        if (v.getId()==R.id.btn1){
            EditText nombre = (EditText) findViewById(R.id.nombre);
            EditText email = (EditText) findViewById(R.id.email);
            EditText password = (EditText) findViewById(R.id.password);
            bd.abrir();
            bd.insertarReg(nombre.getText().toString(),email.getText().toString(),password.getText().toString(),"0");
            bd.cerrar();
            volver =new Intent(Registro.this,Login_D.class);
            startActivity(volver);
            finish();

        }
        if(v.getId()==R.id.btn2){
            volver =new Intent(Registro.this,Login_D.class);
            startActivity(volver);
            finish();
        }

    }
}
