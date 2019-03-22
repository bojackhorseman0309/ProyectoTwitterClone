package com.example.twitteralonso;

import android.content.Intent;
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
        session.setAmigo(nombreBuscado);
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }


}
