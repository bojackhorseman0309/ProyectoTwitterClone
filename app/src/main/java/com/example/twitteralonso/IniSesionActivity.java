package com.example.twitteralonso;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IniSesionActivity extends AppCompatActivity {

    private TwitterDB data;
    private SQLiteDatabase conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_sesion);

        data = new TwitterDB(this, "datos", null, 1);
    }

    public void iniciarSesionVerifica(View view) {
        String correo = ((EditText) findViewById(R.id.etCorreoIS)).getText().toString();
        String contra = ((EditText) findViewById(R.id.etContraIS)).getText().toString();
        boolean verificador = false;

        verificador = consultarProducto(correo, contra);

        if (!verificador){
            Toast.makeText(this, "No coincide", Toast.LENGTH_SHORT);
        }else{
            Intent intent = new Intent(this, TimeLineActivity.class);
            startActivity(intent);
        }
    }

    public boolean consultarProducto(String cor, String contra) {
        conn = data.getReadableDatabase();
        boolean entra = false;

        Cursor fila = conn.rawQuery("SELECT * FROM usuario WHERE correo= '"+cor.trim()+"'", null);
        if (fila.moveToFirst()) {
            do {
                String corDb = fila.getString(1);
                String contraDb = fila.getString(2);
                if (corDb.equalsIgnoreCase(cor) && contraDb.equalsIgnoreCase(contra)) {
                    entra = true;
                }
            } while (fila.moveToNext());

        } else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return entra;
    }

}
