package com.example.twitteralonso;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ConfiguracionActivity extends AppCompatActivity {

    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;
    private Uri filePath;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navHome:
                    Intent home = new Intent(getApplicationContext(), TimeLineActivity.class);
                    startActivity(home);
                    return true;
                case R.id.navBuscar:
                    Intent intBuscar = new Intent(getApplicationContext(), BuscarActivity.class);
                    startActivity(intBuscar);
                    return true;
                case R.id.navNotif:
                    Intent intNotif = new Intent(getApplicationContext(), NotificacionesActivity.class);
                    startActivity(intNotif);
                    return true;
                case R.id.navMensajes:
                    Intent intMensaje = new Intent(getApplicationContext(), MensajesActivity.class);
                    startActivity(intMensaje);
                    return true;
                case R.id.miConfig:
                    Intent intConfig = new Intent(getApplicationContext(), ConfiguracionActivity.class);
                    startActivity(intConfig);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView)findViewById(R.id.bottomNavConfig);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.miConfig).setChecked(true);
        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);
    }

    public void modificarCorreo(View view) {
        String correo = ((EditText) findViewById(R.id.etCorreoConfig)).getText().toString();
        if (correo.isEmpty() || correo == null) {
            Toast.makeText(this, R.string.toastNoNull, Toast.LENGTH_SHORT).show();
        } else {
            if (!emailValido(correo)) {
                Toast.makeText(this, R.string.toastRegActEmail, Toast.LENGTH_SHORT).show();
            } else {
                modificarTweet(correo);
                modificaMensaje(correo);
                modificaMensajeDos(correo);
                modificaAmigo(correo);
                modificaAmigoDos(correo);
                conn = data.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("correo", correo);
                int n = conn.update("usuario", registro, "correo = + '" + session.getNomUsuario().trim() + "'", null);
                session.setNomUsuario(correo);
                conn.close();
                if (n == 1) {
                    Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void modificarTweet(String correo) {

        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();
        String[] nomUsuario = correo.split("@");
        registro.put("nombre", correo);
        registro.put("aliasUsuario", nomUsuario[0]);
        int n = conn.update("tweet", registro, "nombre = + '" + session.getNomUsuario().trim() + "'", null);
        conn.close();
        if (n == 1) {
            //Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
        }
        //conn.close();
    }

    public void modificaMensaje(String correo) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();
        String[] nomUsuario = correo.split("@");
        registro.put("nombre", correo);
        registro.put("idUsuario", nomUsuario[0]);
        int n = conn.update("mensaje", registro, "nombre = + '" + session.getNomUsuario().trim() + "'", null);
        conn.close();
        if (n == 1) {
            //Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
        }
        //conn.close();
    }

    public void modificaMensajeDos(String correo) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("origen", correo);
        int n = conn.update("mensaje", registro, "origen = + '" + session.getNomUsuario().trim() + "'", null);
        conn.close();
        if (n == 1) {
            //Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
        }
        //conn.close();
    }

    //int n = conn.delete("amigo", " correoSesion = + '" + session.getNomUsuario().trim() + "' "
    //                    + " AND correoAmigo =+ '" + session.getAmigo().trim() + "'", null);

    public void modificaAmigo(String correo) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("correoSesion", correo);
        int n = conn.update("amigo", registro, "correoSesion = + '" + session.getNomUsuario().trim() + "'", null);
        conn.close();
        if (n == 1) {
            //Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
        }
        //conn.close();
    }

    public void modificaAmigoDos(String correo) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("correoAmigo", correo);
        int n = conn.update("amigo", registro, "correoAmigo = + '" + session.getNomUsuario().trim() + "'", null);
        conn.close();
        if (n == 1) {
            //Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
        }
        //conn.close();
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
            int n = conn.update("usuario", registro, "correo = + '" + session.getNomUsuario().trim() + "'", null);
            conn.close();
            if (n == 1) {
                Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
            }
        }
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
        int n = conn.update("usuario", registro, "correo = + '" + session.getNomUsuario().trim() + "'", null);
        conn.close();
        if (n == 1) {
            Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.toastNoSePudo, Toast.LENGTH_SHORT).show();
        }
        conn.close();
    }

    public void cerrarSesion(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        session.setAmigo(null);
        session.setContra(null);
        session.setNomUsuario(null);
        startActivity(intent);
    }
}
