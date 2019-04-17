package com.example.twitteralonso;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

        if (correo.isEmpty() || correo == null || contra.isEmpty() || contra == null){
            Toast.makeText(this, R.string.toastNoNull, Toast.LENGTH_SHORT).show();
        }else{
            if (!emailValido(correo)){
                Toast.makeText(this, R.string.toastRegActEmail, Toast.LENGTH_SHORT).show();
            }else{
                registrarUsuarioEnBD(correo, contra);
                Intent intent = new Intent(this, IniSesionActivity.class);
                startActivity(intent);
            }
        }
    }

    public void registrarUsuarioEnBD(String cor, String contra) {
        conn = data.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("correo", cor);
        registro.put("contrasenha", contra);
        registro.put("imagen", R.drawable.ic_dashboard_black_24dp);
        conn.insert("usuario", null, registro);
        conn.close();

        Toast.makeText(this, R.string.toastRegAct, Toast.LENGTH_SHORT).show();
    }

    private static boolean emailValido(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
