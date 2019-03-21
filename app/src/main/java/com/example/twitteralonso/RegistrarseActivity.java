package com.example.twitteralonso;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarseActivity extends AppCompatActivity {

    private TwitterDB data;
    private SQLiteDatabase conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        data = new TwitterDB(this, "datos", null, 1);
    }

    public void registroR(View view) {
        String correo = ((EditText) findViewById(R.id.etCorreoR)).getText().toString();
        String contra = ((EditText) findViewById(R.id.etContraR)).getText().toString();

        insertarProducto(correo, contra);

        Intent intent = new Intent(this, IniSesionActivity.class);
        startActivity(intent);

    }

    public void insertarProducto(String cor, String contra) {
        conn = data.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("correo", cor);
        registro.put("contrasenha", contra);
        conn.insert("usuario", null, registro);
        conn.close();

        Toast.makeText(this, "Se registro satisfactoriamente", Toast.LENGTH_SHORT).show();
    }

}
