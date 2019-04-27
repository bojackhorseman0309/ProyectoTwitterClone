package com.example.twitteralonso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registrarse (View view){
        Intent intent = new Intent(this, RegistrarseActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view){
        Intent intent = new Intent(this, IniSesionActivity.class);
        startActivity(intent);
    }
}
