package com.example.thevenom1215.prueba;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by The venom 1215 on 07/07/2016.
 */
public class Adaptador {
    static final String adapador="DataAdapter";

    private final Context contexto;
    SQLiteDatabase db;
    ConexionDB ayuda;

public Adaptador (Context contexto){
    this.contexto=contexto;
    ayuda = new ConexionDB(contexto);
}
    public Adaptador createDB() throws SQLException{
        try{
            ayuda.crearBD();
        }
        catch(IOException e){
            Toast.makeText(contexto,e.getMessage(),Toast.LENGTH_LONG).show();}
    return this;
    }
    public Adaptador open() throws SQLException{
        try{
            ayuda.abrirBD();
            ayuda.close();
            db = ayuda.getReadableDatabase();
        }catch(Exception e){Toast.makeText(contexto,e.getMessage(),Toast.LENGTH_LONG).show();}
    return this;
    }
}
