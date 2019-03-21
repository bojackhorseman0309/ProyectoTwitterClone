package com.example.twitteralonso;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EscribirTweetActivity extends AppCompatActivity {

    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_tweet);
        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);
    }



    public void tuitear(View view) {
        String tuit = ((EditText) findViewById(R.id.etTweetEscTw)).getText().toString();
        String[] nomUsuario = session.getNomUsuario().split("@");
        insertarTuit(new Tweet(R.drawable.ic_launcher_background, session.getNomUsuario(), nomUsuario[0], tuit, "1", "1", "1"));

        Intent intent = new Intent(this, TimeLineActivity.class);
        startActivity(intent);

    }

    /*"create table tweet (idTweet integer primary key, imagen int," +
            "nombre text, aliasUsuario text, " +
            "tweet text, conResp text, contRetw text, contFav text)"*/

    public void insertarTuit(Tweet t) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();

        registro.put("imagen", t.getImagenPerf());
        registro.put("nombre", t.getNomUsuario());
        registro.put("aliasUsuario", t.getIdNomUsuario());
        registro.put("tweet", t.getTweet());
        registro.put("conResp", t.getContRes());
        registro.put("contRetw", t.getContRetw());
        registro.put("contFav", t.getContFav());

        conn.insert("tweet", null, registro);
        conn.close();
        Toast.makeText(this, "Se ingresó un tweet", Toast.LENGTH_SHORT).show();
    }
}
