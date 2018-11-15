package com.example.dhoser.rapido;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dhoser.rapido.camara.camara;
import com.example.dhoser.rapido.modelo.para_subidatos;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //constantes basuras
    //autoridad del proveedor de contenido que no tengo >c
    public static final String AUTHORITY = "com.example.dhoser.rapido.StubProvider";
    // typo de cuenta
    public static final String ACCOUNT_TYPE = "com.example.dhoser.rapido";
    // nombre de la cuenta
    public static final String ACCOUNT = "Cuenta_basura";
    public static final long intervalo=60;
    Context ctx=this;

    Account micuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ctx=this ;
        micuenta = CrearCuentachalla(this);
        //para_subidatos h=new para_subidatos(this);
        //h.execute();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /*
        ConnectivityManager coneccion=(ConnectivityManager)ctx.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (coneccion!=null){
            NetworkInfo  Nif=coneccion.getActiveNetworkInfo();
            if (Nif!=null){
                if (Nif.getState()== NetworkInfo.State.CONNECTED){
                    Toast.makeText(ctx, "Estamos conectados a la red :D ", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ctx, "No hay na  red D: ", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(ctx, "El Nif esta null ", Toast.LENGTH_LONG).show();
            }
        }*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fr=getSupportFragmentManager();

        if (id == R.id.nav_camera) {
            fr.beginTransaction().replace(R.id.contenedor, new Grabar_D()).commit();
        } else if (id == R.id.nav_gallery) {
            fr.beginTransaction().replace(R.id.contenedor, new Listar()).commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_agregar_remoto) {
            fr.beginTransaction().replace(R.id.contenedor, new Grabar_D()).commit();
        } else if (id == R.id.nav_listar_R) {
            fr.beginTransaction().replace(R.id.contenedor, new Listar_R()).commit();
        } else if (id == R.id.cam) {
            fr.beginTransaction().replace(R.id.contenedor, new camara()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static Account CrearCuentachalla(Context context) {
        // crear cuenta con tipo default
        Account newAccount = new Account(
                ACCOUNT, ACCOUNT_TYPE);
        // tomar cuenta del acount manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(
                        ACCOUNT_SERVICE);
        if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            return newAccount;

        } else {
           return newAccount;


        }
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        micuenta = CrearCuentachalla(this);
        Toast.makeText(ctx, "EStoy en pausa y si lo sincronizo ahora ???", Toast.LENGTH_SHORT).show();
        ContentResolver.requestSync(micuenta,AUTHORITY,Bundle.EMPTY);
        ContentResolver.addPeriodicSync(micuenta,AUTHORITY,Bundle.EMPTY,intervalo);
    }*/
}
