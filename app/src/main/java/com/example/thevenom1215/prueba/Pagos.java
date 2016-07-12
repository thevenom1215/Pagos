package com.example.thevenom1215.prueba;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by The venom 1215 on 08/07/2016.
 */
public class Pagos extends Activity {

    SQLiteOpenHelper    ayuda;
    SQLiteDatabase      database;
    ContentValues       datos;
    Cursor              cursor;

    DateFormat formato = new SimpleDateFormat("yyyy/mm/dd");
    Date fecha = new Date();

    Button cancelar,pagar;
    EditText claveContrato,costo,nombre,pago,pagos,paquete,parcialidad,saldo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagos);

        cancelar        =   (Button)findViewById(R.id.cancelar);
        pagar           =   (Button)findViewById(R.id.pagar);
        claveContrato   =   (EditText)findViewById(R.id.claveContrato);
        costo           =   (EditText)findViewById(R.id.costo);
        nombre          =   (EditText)findViewById(R.id.nombre);
        pago            =   (EditText)findViewById(R.id.pago);
        pagos           =   (EditText)findViewById(R.id.pagos);
        paquete         =   (EditText)findViewById(R.id.paquete);
        parcialidad     =   (EditText)findViewById(R.id.parcialidad);
        saldo           =   (EditText)findViewById(R.id.saldo);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrir = new Intent(v.getContext(),Recibos.class);
            startActivity(abrir);
            }
        });

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos = new ContentValues();
try {
    datos.put("STS_PAGO"        ,12       );
    datos.put("ID_CONTR"        ,claveContrato.getText().toString());
    datos.put("CVE_CONTR"       ,claveContrato.getText().toString());
//datos.put("ID_PAGO"         ,   );
//datos.put("ID_RECIBO"       ,   );
//datos.put("NO_RECIBO"       ,   );
//datos.put("NO_PARCIAL"      ,   );
//datos.put("TIP_PARCIAL"     ,   );
//datos.put("FEC_GENREC"      ,   );
//datos.put("FEC_RECPAG"      ,     );
    datos.put("FEC_PAGO"        ,  formato.format(fecha));
//datos.put("INV_INICIAL"     ,    );
//datos.put("IMPTO_RECIBO"    ,   );
//datos.put("IMP_CALREC "     ,    );
//datos.put("IMP_ACOBRAR"     ,    );
//    datos.put("IMP_PARCIAL"     ,    );
//    datos.put("IMP_DESC"        ,       );
//    datos.put("IMP_COBRADO"     ,    );
    datos.put("SDO_TOTAL"       ,saldo.getText().toString());
//    datos.put("CANT_LETRA"      ,     );
//    datos.put("ID_COBRADOR"     ,    );
//    datos.put("CVE_COBRADOR"    ,   );
//    datos.put("NO_REFBANC"      ,     );
//    datos.put("NO_APRBANC"      ,     );
    datos.put("ID_SUCURSAL"     ,    );
    datos.put("FEC_ACTREG"      ,  formato.format(fecha));
    datos.put("USR_ACTREG"      ,     );
    datos.put("OBSERVACIONES"   ,  );
    datos.put("FEC_CADUC"       ,      );
    datos.put("ID_TPAGO"        ,       );
    datos.put("NO_CHEQUE"       ,      );
    datos.put("LIN_TRANSAC"     ,    );
    datos.put("NO_TRANSAC"      ,     );
    datos.put("ID_TPAGO"        ,       );
    datos.put("NO_CHEQUE"       ,      );
    datos.put("LIN_TRANSAC"     ,    );
    datos.put("NO_TRANSAC"      ,     );
    datos.put("CVE_SUCURSAL"    ,   );
    datos.put("NOT_RECIBO"      ,     );
                database.insert("",null,datos);
}
catch(Exception e){
    Toast.makeText(v.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
}

            }
        });

    }


}
