package com.example.dhoser.rapido;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static android.Manifest.permission.READ_CONTACTS;


public class Login_D extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__d);
        final Context ctx=this;

        Button entrar=(Button)findViewById(R.id.entrar_ld);

        entrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager coneccion=(ConnectivityManager)ctx.getSystemService(Service.CONNECTIVITY_SERVICE);
                if (coneccion!=null){
                    NetworkInfo Nif=coneccion.getActiveNetworkInfo();
                    if (Nif!=null){
                        if (Nif.getState()== NetworkInfo.State.CONNECTED){
                            Toast.makeText(ctx, "Estamos conectados a la red :D ", Toast.LENGTH_LONG).show();
                            login_R();
                        }else{
                            Toast.makeText(ctx, "No hay na  red D: ", Toast.LENGTH_LONG).show();
                            login_L();
                        }
                    }else{
                        Toast.makeText(ctx, "El Nif esta null ", Toast.LENGTH_LONG).show();
                        login_L();
                    }
                }

            }
        });
    }

    public Login_D() {
    }

    public void login_R(){
        TextView email=(TextView)findViewById(R.id.email_ld);
        TextView pass=(TextView)findViewById(R.id.password_ld);
        AsyncHttpClient cliente =new AsyncHttpClient();
        RequestParams parametros= new RequestParams();
        parametros.add("email",email.getText().toString());
        parametros.add("password",pass.getText().toString());
        String url="http://192.168.56.1/android/login.php";
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    if (statusCode==200){
                        JSONObject objetoj=new JSONObject(new String(responseBody));
                        String usuario=objetoj.getString("login");
                        if (!TextUtils.isEmpty(usuario)){

                            startActivity(new Intent(Login_D.this, MainActivity.class));

                        }else {
                            Toast.makeText(Login_D.this, "Usuario o password incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch (JSONException e){
                    Toast.makeText(Login_D.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Login_D.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }

        });
    }
    public void login_L() {
        int cont = 0;
        TextView email = (TextView) findViewById(R.id.email_ld);
        TextView pass = (TextView) findViewById(R.id.password_ld);
        BaseDatos bd = new BaseDatos(this);
        String hi = bd.leer();
        String[] LINEAS = hi.split(",");
        String correo=email.getText().toString();
       /* for (String credential : LINEAS) {
            System.out.println(credential);
            String[] algo = credential.split(":");
            System.out.println(algo[0]);
            int i;
           for(i=0; i==algo.length ;i++) {

               if (algo[i].equals(correo)){
               // Account exists, return true if the password matches.
                   startActivity(new Intent(Login_D.this, MainActivity.class));
                   break;
               }
           }
            cont += 1;
            if (cont == algo.length) {
                email.setError("El usuario no existe");
            }


        }*/
    }

}

