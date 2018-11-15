package com.example.dhoser.rapido;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Listar extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BaseDatos bd ;

    Context ctx;

    private String mParam1;
    private String mParam2;



    public Listar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mivista= inflater.inflate(R.layout.fragment_listar, container, false);
        ctx=this.getActivity();
        TextView txtmostar=(TextView)mivista.findViewById(R.id.txtmostrar);
        bd=new BaseDatos(ctx);
        bd.abrir();
        txtmostar.setText("Deberia mostrar esto al menos "+bd.leer().toString());
        bd.cerrar();
        return mivista;
    }

}
