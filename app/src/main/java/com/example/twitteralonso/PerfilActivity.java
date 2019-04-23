package com.example.twitteralonso;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

        recycler = (RecyclerView) findViewById(R.id.rvPerfil);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new PerfilAdapter(items, getApplicationContext());
        recycler.setAdapter(adapter);
    }

     /*db.execSQL("create table tweet (idTweet integer primary key, imagen int," +
             "nombre text, aliasUsuario text, " +
             "tweet text, conResp text, contRetw text, contFav text)");*/

    public List<Tweet> consultarTuits(String nomBusc) {
        conn = data.getReadableDatabase();
       Bitmap bitmap = getImage(nomBusc);
        List<Tweet> listaTuit = new ArrayList<>();

        Cursor fila = conn.rawQuery("SELECT * FROM tweet WHERE nombre = '" + nomBusc.trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                listaTuit.add(new Tweet(bitmap, fila.getString(2),
                        fila.getString(3), fila.getString(4),
                        fila.getString(5), fila.getString(6), fila.getString(7)));
            } while (fila.moveToNext());
        } else {
            Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return listaTuit;
    }

    public String consultaImagenAmigo (String amigo){
        conn = data.getReadableDatabase();
        String aux = "";
        Cursor fila = conn.rawQuery("SELECT * FROM usuario WHERE correo = '" + amigo.trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                aux = fila.getString(3);
            } while (fila.moveToNext());
        } else {
            Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return aux;
    }

    public Bitmap getImage(String amigo){

        Cursor cur = conn.rawQuery("SELECT * FROM usuario WHERE correo = '" + amigo.trim() + "'", null);

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



    public void añadirAmigo(View view) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("correoSesion", session.getNomUsuario());
        registro.put("correoAmigo", session.getAmigo());

        conn.insert("amigo", null, registro);
        conn.close();
        Toast.makeText(this, R.string.toastAgregaSeguidor, Toast.LENGTH_SHORT).show();
    }
}
