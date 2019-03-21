package com.example.twitteralonso;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BuscarActivity extends AppCompatActivity {
    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);
    }

    public void buscarUsuario(View view){
        String nombreBuscado = ((EditText)findViewById(R.id.etBuscarBA)).getText().toString();

    }

    public void consultarProducto(String nomBusc) {
        conn = data.getReadableDatabase();
        boolean entra = false;

        Cursor fila = conn.rawQuery("SELECT * FROM usuario WHERE correo= '"+nomBusc.trim()+"'", null);
        if (fila.moveToFirst()) {
            do {


            } while (fila.moveToNext());

        } else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
        conn.close();
    }
}
