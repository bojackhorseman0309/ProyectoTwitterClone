package com.example.twitteralonso;

import android.content.ContentValues;
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

    public void mandarMensaje(View view) {
        String dest = ((EditText) findViewById(R.id.etDestinatarioMM)).getText().toString();
        String mensj = ((EditText) findViewById(R.id.etMensajeMM)).getText().toString();
        if (TextUtils.isEmpty(dest) || TextUtils.isEmpty(mensj)) {
            Toast.makeText(this, R.string.toastNoNull, Toast.LENGTH_SHORT).show();
        } else {
            String[] nomUsuario = session.getNomUsuario().split("@");
            insertarMensaje(dest, mensj, nomUsuario[0]);
        }
    }

    public void insertarMensaje(String dest, String mensj, String idUsuario) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();
        Bitmap bitmap = getImage();
        byte[] imagen = getBitmapAsByteArray(bitmap);
        registro.put("imagen", imagen);
        registro.put("nombre", session.getNomUsuario());
        registro.put("idUsuario", idUsuario);
        registro.put("fecha", "hoy");
        registro.put("mensaje", mensj);
        registro.put("origen", dest);
        conn.insert("mensaje", null, registro);
        conn.close();

        Toast.makeText(this, R.string.toastRegistroMensaje, Toast.LENGTH_SHORT).show();
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public Bitmap getImage(){

        Cursor cur = conn.rawQuery("SELECT imagen FROM usuario WHERE correo = '" + session.getNomUsuario().trim() + "'", null);

        if (cur.moveToFirst()){
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null;
    }
}
