package com.example.dhoser.rapido.camara;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhoser.rapido.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class camara extends Fragment {


    private FusedLocationProviderClient client;
    ImageView verimg;
    public camara() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_camara, container, false);

        client =LocationServices.getFusedLocationProviderClient(getActivity());
        final TextView txtubicacion=(TextView)vista.findViewById(R.id.txtubicacion);
        verimg=(ImageView)vista.findViewById(R.id.vistaimg);
        Button btncam=(Button)vista.findViewById(R.id.btncam);
        requespermission();
        final TextView txtlat=(TextView)vista.findViewById(R.id.txtlat);
        btncam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ActivityCompat.checkSelfPermission(getActivity(),ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    return;
                }
                client.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location!=null){
                            txtubicacion.setText(location.toString());
                            txtlat.setText("La Latitud es :"+location.getLatitude()+ "La Longitud es :"+ location.getLongitude());
                        }
                    }
                });


                Intent tomarf= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivity(tomarf);
                //txtubicacion.setText("La Latitud es:"+ latitud +" y la longitud es "+longitud );
                startActivityForResult(tomarf,10);
            }
        });
        return vista;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode==10 ){
            Bundle extras= data.getExtras();
            Bitmap Imagen=(Bitmap)extras.get("data");
            verimg.setImageBitmap(Imagen);
        }

    }

    public void requespermission(){
        ActivityCompat.requestPermissions(getActivity(),new String[]{ACCESS_FINE_LOCATION},1);
    }
}
