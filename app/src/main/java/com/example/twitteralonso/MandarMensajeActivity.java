package com.example.twitteralonso;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MandarMensajeActivity extends AppCompatActivity {

    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandar_mensaje);

        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);

    }

    /* db.execSQL("create table mensaje (idMens integer primary key, imagen int, nombre text, idUsuario text, fecha text, mensaje text)"); */

    public void mandarMensaje(View view){
        String dest = ((EditText)findViewById(R.id.etDestinatarioMM)).getText().toString();
        String mensj = ((EditText)findViewById(R.id.etMensajeMM)).getText().toString();
        String[] nomUsuario = dest.split("@");

        insertarMensaje(dest, mensj, nomUsuario[0]);


    }

    public void insertarMensaje(String dest, String mensj, String idUsuario) {
        conn = data.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("imagen", R.drawable.ic_launcher_background);
        registro.put("nombre", dest);
        registro.put("idUsuario", idUsuario);
        registro.put("fecha", "hoy");
        registro.put("mensaje", mensj);
        registro.put("origen", session.getNomUsuario());
        conn.insert("mensaje", null, registro);
        conn.close();

        Toast.makeText(this, "Se registro su mensaje", Toast.LENGTH_SHORT).show();
    }
}
