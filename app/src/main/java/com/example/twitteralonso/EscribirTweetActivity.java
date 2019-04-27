package com.example.twitteralonso;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

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
        Bitmap bitmap = getImage();
        String[] nomUsuario = session.getNomUsuario().split("@");
        insertarTuit(new Tweet(bitmap, session.getNomUsuario(),
                nomUsuario[0], tuit, 0));

        Intent intent = new Intent(this, TimeLineActivity.class);
        startActivity(intent);

    }

    public Bitmap getImage(){
        conn = data.getReadableDatabase();
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



    /*"create table tweet (idTweet integer primary key, imagen int," +
            "nombre text, aliasUsuario text, " +
            "tweet text, conResp text, contRetw text, contFav text)"*/

    public void insertarTuit(Tweet t) {
        conn = data.getWritableDatabase();
        ContentValues registro = new ContentValues();
        byte[] imagen =  getBitmapAsByteArray(t.getImagenPerf());
        registro.put("imagen", imagen);
        registro.put("nombre", t.getNomUsuario());
        registro.put("aliasUsuario", t.getIdNomUsuario());
        registro.put("tweet", t.getTweet());
        registro.put("contFav", t.getContFav());

        conn.insert("tweet", null, registro);
        conn.close();
        Toast.makeText(this, "Se ingresó un tweet", Toast.LENGTH_SHORT).show();
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
