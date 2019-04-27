package com.example.twitteralonso;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ConfiguracionActivity extends AppCompatActivity {

    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;
    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);
    }

    public void modificarCorreo(View view) {
        conn = data.getWritableDatabase();
        String correo = ((EditText) findViewById(R.id.etCorreoConfig)).getText().toString();
        if (correo.isEmpty() || correo == null) {
            Toast.makeText(this, R.string.toastNoNull, Toast.LENGTH_SHORT).show();
        } else {
            if (!emailValido(correo)) {
                Toast.makeText(this, R.string.toastRegActEmail, Toast.LENGTH_SHORT).show();
            } else {
                ContentValues registro = new ContentValues();
                registro.put("correo", correo);
                int n = conn.update("usuario", registro, "correo = + '"+session.getNomUsuario().trim()+"'" , null);
                session.setNomUsuario(correo);
                conn.close();
                if (n == 1) {
                    Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
                }
            }

        }

        conn.close();
    }

    private static boolean emailValido(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void modificarPwd(View view) {
        conn = data.getWritableDatabase();
        String pwd = ((EditText) findViewById(R.id.etPwdConfig)).getText().toString();
        if (pwd.isEmpty() || pwd == null) {
            Toast.makeText(this, R.string.toastNoNull, Toast.LENGTH_SHORT).show();
        } else {
                ContentValues registro = new ContentValues();
                registro.put("contrasenha", pwd);
                int n = conn.update("usuario", registro, "correo = + '"+session.getNomUsuario().trim()+"'", null);
                conn.close();
                if (n == 1) {
                    Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
                }
        }
        conn.close();
    }

    public void subir(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), 71);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent datas) {
        super.onActivityResult(requestCode, resultCode, datas);
        if (requestCode == 71 && resultCode == RESULT_OK) {
            filePath = datas.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] imagen = getBitmapAsByteArray(bitmap);
            modificarImagen(imagen);

        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public void modificarImagen(byte[] imagen) {

        conn = data.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("imagen", imagen);
                int n = conn.update("usuario", registro, "correo = + '"+session.getNomUsuario().trim()+"'" , null);
                conn.close();
                if (n == 1) {
                    Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
                }
        conn.close();
    }

    public void cerrarSesion(View view){
        Intent intent = new Intent(this, TimeLineActivity.class);
        session.setAmigo(null);
        session.setContra(null);
        session.setNomUsuario(null);
        startActivity(intent);
    }
}
