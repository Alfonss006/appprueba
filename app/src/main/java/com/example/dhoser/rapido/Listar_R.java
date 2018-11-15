package com.example.dhoser.rapido;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.DataAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

import cz.msebera.android.httpclient.Header;


public class Listar_R extends Fragment {

    public Listar_R() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_listar__r, container, false);
        final TextView mostra=(TextView) vista.findViewById(R.id.mostra);
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams parameters=new RequestParams();
        final Context ctx =getActivity();
        String url="http://10.0.2.2/android/listar.php";

        client.post(url, parameters, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    if(statusCode==200) {
                        JSONObject object = new JSONObject(new String(responseBody));
                        String resul = object.getString("lista");

                        mostra.setText("aqui esta la lista se supone "+resul);
                    }
                    else{
                        Toast.makeText(ctx,"ERROR Al mostrar",Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(ctx,"ERROR de Conectividad",Toast.LENGTH_LONG).show();
            }
        });

        return vista;
    }

}
