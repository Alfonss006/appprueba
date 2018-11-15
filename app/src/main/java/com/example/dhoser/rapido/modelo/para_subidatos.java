package com.example.dhoser.rapido.modelo;

import android.content.Context;
import android.os.AsyncTask;

import com.example.dhoser.rapido.BaseDatos;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class para_subidatos  {

    Context Ctx;
    public para_subidatos(Context ctx) {
        Ctx=ctx;
    }

    
    



    public void da() {

            BaseDatos bd =new BaseDatos(Ctx);
            String url="http://10.0.2.2/android/agregar.php";
            bd.abrir();
            ArrayList<Usuarios> algo =bd.buscar();
            for(Usuarios a:algo) {
                SyncHttpClient client = new SyncHttpClient();
                RequestParams parameters = new RequestParams();
                parameters.add("name",a.getNombre());
                parameters.add("mail",a.getEmail());
                parameters.add("pass",a.getPassword());
                client.post(url,parameters,new JsonHttpResponseHandler() {
                            @Override
                            public void onStart() {

                            }


                        }
                );


            }


    }
}
