package com.example.thevenom1215.prueba;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Recibos extends Activity {

    SQLiteOpenHelper ayuda;
    SQLiteDatabase database;
    Cursor cursor;

    Button buscar,historial,limpiar,pagar;
    EditText calle,colonia,materno,nombre,numeroContrato,paterno;
    ListView recibos;
    RadioButton contrato,domicilio,titular;

    final ArrayList <String> datos = new ArrayList<String>();
    ArrayAdapter adaptador;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibos);

    buscar          =   (Button)findViewById(R.id.buscar);
    historial       =   (Button)findViewById(R.id.historial);
    limpiar         =   (Button)findViewById(R.id.limpiar);
    pagar           =   (Button)findViewById(R.id.pagar);
    calle           =   (EditText)findViewById(R.id.calle);
    colonia         =   (EditText)findViewById(R.id.colonia);
    materno         =   (EditText)findViewById(R.id.materno);
    nombre          =   (EditText)findViewById(R.id.nombre);
    numeroContrato  =   (EditText)findViewById(R.id.numeroContrato);
    paterno         =   (EditText)findViewById(R.id.paterno);
    recibos         =   (ListView)findViewById(R.id.recibos);
    contrato        =   (RadioButton)findViewById(R.id.contrato);
    domicilio       =   (RadioButton)findViewById(R.id.domicilio);
    titular         =   (RadioButton)findViewById(R.id.titular);

        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,datos);

buscar.setOnClickListener(new View.OnClickListener() {
String sql;
    @Override
    public void onClick(View v) {

        if(contrato.isChecked()){
            sql=sql+" select b.ID_CONTR, a.NOM_SOLICIT_D, a.AP_PATSOLICIT_D, a.AM_PATSOLICIT_D,a.CALLE_COBRO from MFRF_M_SOLICITANTES_D a, MFRF_M_CONTPREV_H b where a.ID_SOLICITANTE = b.ID_SOLICITANTE and ID_CONTR = '"+contrato.getText().toString()+"'";}

        if(domicilio.isChecked()){
            sql="select b.ID_CONTR, a.NOM_SOLICIT_D, a.AP_PATSOLICIT_D, a.AM_PATSOLICIT_D,a.CALLE_COBRO from MFRF_M_SOLICITANTES_D a, MFRF_M_CONTPREV_H b, MFRF_C_COLONIAS c where a.ID_SOLICITANTE = b.ID_SOLICITANTE and CALLE_COBRO = '"+
                    calle+"' and c.DES_COLONIA='"+colonia.getText().toString()+"' and b.ID_COLONIA = c.ID_COLONIA";
        }

        if(titular.isChecked()){
            sql="select b.ID_CONTR, a.NOM_SOLICIT_D, a.AP_PATSOLICIT_D, a.AM_PATSOLICIT_D,a.CALLE_COBRO from MFRF_M_SOLICITANTES_D a, MFRF_M_CONTPREV_H b where NOM_SOLICIT_D = '"+nombre.getText().toString()+"' and AP_PATSOLICIT_D = '"+paterno.getText().toString()
                    +"' and AM_PATSOLICIT_D = '"+materno.getText().toString()+"'";
        }
try {
        cursor=database.rawQuery(sql,null);
        int i=0;
        while(cursor.moveToNext()){
            datos.add(i,"No contrato"+cursor.getString(0)+"\nNombre: "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\nDireccion: "+cursor.getString(4));
        i++;
        }
    recibos.setAdapter(adaptador);}
catch(Exception e){
    Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
}

    }
});

historial.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

limpiar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});

pagar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent abrir = new Intent(v.getContext(),Pagos.class);
        startActivity(abrir);
    }
});

}

}
