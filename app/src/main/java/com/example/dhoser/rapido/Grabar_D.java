package com.example.dhoser.rapido;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.DataAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class Grabar_D extends Fragment {

    BaseDatos db;
    Context ctx;
    EditText nombre_d;
    EditText correo_d;
    EditText pass_d;
    public static final String AUTHORITY = "com.example.dhoser.rapido.StubProvider";
    // typo de cuenta
    public static final String ACCOUNT_TYPE = "com.example.dhoser.rapido";
    // nombre de la cuenta
    public static final String ACCOUNT = "Cuenta_basura";
    public  Account micuenta=CrearCuentachalla(getActivity());
    public Grabar_D() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_grabar__d, container, false);
        ctx=getActivity();



        final ConnectivityManager coneccion=(ConnectivityManager)ctx.getSystemService(Service.CONNECTIVITY_SERVICE);
        nombre_d =(EditText)vista.findViewById(R.id.nombre_d);
        correo_d =(EditText)vista.findViewById(R.id.correo_d);
        pass_d =(EditText)vista.findViewById(R.id.pass_d);

        Button btn_gd=(Button)vista.findViewById(R.id.btn_gd);
        btn_gd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (coneccion!=null){
                    NetworkInfo Nif=coneccion.getActiveNetworkInfo();
                    if (Nif!=null){
                        if (Nif.getState()== NetworkInfo.State.CONNECTED){
                            Toast.makeText(ctx, "Estamos conectados a la red :D ", Toast.LENGTH_LONG).show();
                            GrabarLocal("0");
                            GrabarRemoto();
                            nombre_d.setText(""); correo_d.setText("");pass_d.setText("");
                        }else{
                            Toast.makeText(ctx, "No hay na  red D: ", Toast.LENGTH_LONG).show();
                            GrabarLocal("1");
                            nombre_d.setText(""); correo_d.setText("");pass_d.setText("");
                        }
                    }else{
                        Toast.makeText(ctx, "El Nif esta null ", Toast.LENGTH_LONG).show();
                        GrabarLocal("1");
                        nombre_d.setText(""); correo_d.setText("");pass_d.setText("");
                    }
                }

            }
        });
        return vista;

    }

    public void GrabarLocal(String d){
        db=new BaseDatos(ctx);
        db.abrir();
        db.insertarReg(nombre_d.getText().toString(),correo_d.getText().toString(), pass_d.getText().toString(),d);
        db.cerrar();
        nombre_d.findFocus();
        Toast.makeText(ctx,"Registro creado Localmente :D ",Toast.LENGTH_LONG).show();

        Bundle settingsBundle = new Bundle();
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_MANUAL, true);
        settingsBundle.putBoolean(
                ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
                ContentResolver.requestSync(micuenta, AUTHORITY, settingsBundle);

    }
    public void GrabarRemoto(){
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams parameters=new RequestParams();
        parameters.add("name",nombre_d.getText().toString());
        parameters.add("mail", correo_d.getText().toString());
        parameters.add("pass", pass_d.getText().toString());
        final Context ctx =getActivity();
        String url="http://10.0.2.2/android/agregar.php";
        client.post(url, parameters, new DataAsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject objecto = new JSONObject(new String(responseBody));
                    String obj=objecto.getString("status");
                    if(statusCode==200&& obj.equals("ok")){
                        Toast.makeText(ctx, "Registro creado  "+obj , Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ctx, "Error al crear el Registro", Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(ctx, "Error al conectar" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Account CrearCuentachalla(Context context) {
        // crear cuenta con tipo default
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
                          return newAccount;
    }

}
