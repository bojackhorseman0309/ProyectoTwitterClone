package com.example.twitteralonso;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimeLineActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private String correo;
    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;


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
        setContentView(R.layout.activity_time_line);
        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        List<Tweet> items = mezclaLista();

        recycler = (RecyclerView) findViewById(R.id.rvTimeline);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new TweetAdapter(items);
        recycler.setAdapter(adapter);
    }

    public void escribirTuit(View view) {
        Intent intent = new Intent(this, EscribirTweetActivity.class);
        startActivity(intent);
    }

    public List<Tweet> consultarTuit() {
        conn = data.getReadableDatabase();
        List<Tweet> listaAux = new ArrayList<>();
        Cursor fila = conn.rawQuery("SELECT * FROM tweet WHERE nombre = '" + session.getNomUsuario().trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                listaAux.add(new Tweet(fila.getInt(1), fila.getString(2), fila.getString(3),
                        fila.getString(4), fila.getString(5), fila.getString(6),
                        fila.getString(7)));
            } while (fila.moveToNext());
        } else {
            Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return listaAux;
    }

    public List<Tweet> consultarAmigo() {
        conn = data.getReadableDatabase();
        List<Tweet> listaAux = new ArrayList<>();

        Cursor fila = conn.rawQuery("SELECT correoAmigo FROM amigo WHERE correoSesion = '" + session.getNomUsuario().trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                listaAux.addAll(consultarTweetAmigo(fila.getString(0)));
            } while (fila.moveToNext());
        } else {
            Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return listaAux;
    }

    public List<Tweet> consultarTweetAmigo(String nomBusc) {
        conn = data.getReadableDatabase();
        List<Tweet> listaAmigo = new ArrayList<>();

        Cursor fila = conn.rawQuery("SELECT * FROM tweet WHERE nombre = '" + nomBusc.trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                listaAmigo.add(new Tweet(R.drawable.ic_launcher_background, fila.getString(2),
                        fila.getString(3), fila.getString(4), fila.getString(5),
                        fila.getString(6), fila.getString(7)));
            } while (fila.moveToNext());
        } else {
            Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return listaAmigo;
    }

    public List<Tweet> mezclaLista() {
        List<Tweet> listaFinal = new ArrayList<>();
        listaFinal.addAll(consultarAmigo());
        listaFinal.addAll(consultarTuit());
        return listaFinal;
    }


}
