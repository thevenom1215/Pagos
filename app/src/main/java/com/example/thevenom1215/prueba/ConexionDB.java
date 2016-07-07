package com.example.thevenom1215.prueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ConexionDB extends SQLiteOpenHelper {

    static String TAG;
    static String path="";
    static String nombre_DB="Prospectos";
    SQLiteDatabase database;
    final Context contexto;

    public ConexionDB (Context context)
    {
        super(context, nombre_DB, null, 1);// 1? Its database Version
        if(android.os.Build.VERSION.SDK_INT >= 17){
            path =context.getApplicationInfo().dataDir+"/databases/";
        }
        else{
            path="/data/data/"+context.getPackageName()+"/databases/";
        }
        path ="/data/data/sidd.mfrf/databases/";
        this.contexto = context;
    }

    public void crearBD () throws IOException {

        boolean existebd = estatusBD();
        if(!existebd){
            this.getReadableDatabase();
            this.close();
            try{
                copiarBD();
                Log.e(TAG,"Base de datos creada");
            }catch(IOException e){Toast.makeText(contexto,e.getMessage(),Toast.LENGTH_LONG).show();}
        }
    }

    private boolean estatusBD(){
        File archivodb = new File(path+nombre_DB);
        return archivodb.exists();
    }

    public void  copiarBD () throws IOException{
        InputStream input = contexto.getAssets().open(nombre_DB);
        String archivoresultante = path+nombre_DB;
        OutputStream output = new FileOutputStream(archivoresultante);
        byte [] buffer = new byte[1024];
        int length;
        while((length=input.read(buffer))>0){
            output.write(buffer,0,length);
        }
        output.flush();
        output.close();
        input.close();
    }

    public boolean abrirBD()throws IOException{
        String direccion = path+nombre_DB;
        database = SQLiteDatabase.openDatabase(direccion,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        return database != null;
    }

    public synchronized void close(){
        if(database!=null){
            database.close();
            super.close();

        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
