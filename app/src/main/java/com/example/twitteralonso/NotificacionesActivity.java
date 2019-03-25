package com.example.twitteralonso;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);
        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);

        List<Notificacion> items = consultarAmigo();

        recycler = (RecyclerView) findViewById(R.id.rvNotificaciones);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new NotificacionAdapter(items);
        recycler.setAdapter(adapter);
    }

    public List<Notificacion> consultarAmigo() {
        conn = data.getReadableDatabase();
        List<Notificacion> listaAux = new ArrayList<>();

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

    public List<Notificacion> consultarTweetAmigo(String nomBusc) {
        conn = data.getReadableDatabase();
        List<Notificacion> listaAmigo = new ArrayList<>();

        Cursor fila = conn.rawQuery("SELECT tweet FROM tweet WHERE nombre = '" + nomBusc.trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                Resources res = getResources();
                String nom = String.format(res.getString(R.string.nuevoTuitDe), nomBusc);
                listaAmigo.add(new Notificacion(R.drawable.ic_launcher_background, nom, fila.getString(0)));
            } while (fila.moveToNext());
        } else {
            Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return listaAmigo;
    }

}
