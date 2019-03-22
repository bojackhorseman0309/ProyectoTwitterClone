package com.example.twitteralonso;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);

        List<Tweet> items = consultarTuits(session.getAmigo());


        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.rvPerfil);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new PerfilAdapter(items);
        recycler.setAdapter(adapter);
    }

     /*db.execSQL("create table tweet (idTweet integer primary key, imagen int," +
             "nombre text, aliasUsuario text, " +
             "tweet text, conResp text, contRetw text, contFav text)");*/

    public List<Tweet> consultarTuits(String nomBusc) {
        conn = data.getReadableDatabase();
        List<Tweet> listaTuit = new ArrayList<>();

        Cursor fila = conn.rawQuery("SELECT * FROM tweet WHERE aliasUsuario = '"+nomBusc.trim()+"'", null);
        if (fila.moveToFirst()) {
            do {

                listaTuit.add(new Tweet(fila.getInt(1), fila.getString(2),
                        fila.getString(3), fila.getString(4),
                        fila.getString(5), fila.getString(6), fila.getString(7)));

            } while (fila.moveToNext());

        } else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return listaTuit;
    }
}
