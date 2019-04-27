package com.example.twitteralonso;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

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

        if (correo.isEmpty() || correo == null || contra.isEmpty() || contra == null) {
            Toast.makeText(this, R.string.toastNoNull, Toast.LENGTH_SHORT).show();
        } else {
            if (!emailValido(correo)) {
                Toast.makeText(this, R.string.toastRegActEmail, Toast.LENGTH_SHORT).show();
            } else {
                if (consultarUsuarioRepetido(correo)) {
                    Toast.makeText(this, R.string.toastUsuarioExiste, Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuarioEnBD(correo, contra);
                    Intent intent = new Intent(this, IniSesionActivity.class);
                    startActivity(intent);
                }

            }
        }
    }

    public void registrarUsuarioEnBD(String cor, String contra) {
        conn = data.getWritableDatabase();
        Bitmap image = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.huevo);
        byte[] imagen = getBitmapAsByteArray(image);
        ContentValues registro = new ContentValues();
        registro.put("correo", cor);
        registro.put("contrasenha", contra);
        registro.put("imagen", imagen);
        conn.insert("usuario", null, registro);
        conn.close();

        Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
    }

    private static boolean emailValido(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


    public Bitmap getImage(int i) {

        String qu = "select img  from table where feedid=" + i;
        Cursor cur = conn.rawQuery(qu, null);

        if (cur.moveToFirst()) {
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null;
    }

    public boolean consultarUsuarioRepetido(String cor) {
        conn = data.getReadableDatabase();
        boolean entra = false;

        Cursor fila = conn.rawQuery("SELECT * FROM usuario WHERE correo = '" + cor.trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                entra = true;
            } while (fila.moveToNext());

        } else {
            //Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return entra;
    }

}
