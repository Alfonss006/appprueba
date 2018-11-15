package com.example.dhoser.rapido;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dhoser.rapido.modelo.Usuarios;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos(Context context) {
        super(context, "Rapidito", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String  query = "CREATE TABLE personas (" +
                _ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT,"+
                "email TEXT,"+
                "password TEXT,"+
                "flag TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int version_ant, int version_nue){
        db.execSQL("DROP TABLE personas");
        onCreate(db);
    }

    public void eliminar_tabla(){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("personas", null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME ='personas'" );
    }

    public void insertarReg(String nom,String ema, String pass , String hi){
        ContentValues valores  = new ContentValues();
        valores.put("nombres", nom);
        valores.put("email", ema);
        valores.put("password", pass);
        valores.put("flag", hi);
        this.getWritableDatabase().insert("personas", null, valores);
    }

    public String eliminar(String email){
        String[] args = new String[] {email};
        this.getReadableDatabase().execSQL("DELETE FROM personas WHERE email=?", args);
        return "Registro Eliminado";

    }
    public ArrayList<Usuarios> buscar(){
        ArrayList<Usuarios> resultado=new ArrayList<Usuarios>();
        String[] campos = new String[] {_ID, "nombres", "email", "password"};
        String[] args = new String[] {};

        Cursor c = this.getReadableDatabase().query("personas", campos, "flag=1", args, null, null, null);
        int id, nom, ema, pass;

        id= c.getColumnIndex(_ID);
        nom= c.getColumnIndex("nombres");
        ema= c.getColumnIndex("email");
        pass= c.getColumnIndex("password");

        while(c.moveToNext())
        {
            Usuarios s= new Usuarios(c.getString(nom), c.getString(ema),c.getString(pass));
            resultado.add(s);
        }
        return resultado;

    }
    public String leer(){
        String resultado="";

        String filas[] = {_ID, "nombres", "email", "password"};

        Cursor c = this.getReadableDatabase().query("personas", filas,null, null, null, null, null);
        int id,nom, ema, pass;

        id= c.getColumnIndex(_ID);
        nom=c.getColumnIndex("nombres");
        ema= c.getColumnIndex("email");
        pass= c.getColumnIndex("password");

        while(c.moveToNext())
        {
            resultado += c.getString(id)+":" + c.getString(nom) +":"+c.getString(ema)+":"+c.getString(pass)+", \n";
        }

        return resultado;
    }


    public void abrir(){
        this.getWritableDatabase();
    }


    public void cerrar(){
        this.close();
    }

}