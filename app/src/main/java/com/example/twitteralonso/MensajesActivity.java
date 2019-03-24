package com.example.twitteralonso;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MensajesActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private TwitterDB data;
    private SQLiteDatabase conn;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);

        List<Mensaje> items = consultaMensaje();


        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.rvMensajes);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new MensajeAdapter(items);
        recycler.setAdapter(adapter);
    }

    public void aMensaje(View view) {

        Intent intent = new Intent(this, MandarMensajeActivity.class);
        startActivity(intent);

    }

    public List<Mensaje> consultaMensaje() {
        conn = data.getReadableDatabase();
        List<Mensaje> itemsAux = new ArrayList<>();
        Cursor fila = conn.rawQuery("SELECT * FROM mensaje WHERE nombre = '"+session.getNomUsuario().trim()+"'", null);
        if (fila.moveToFirst()) {
            do {

                itemsAux.add(new Mensaje(R.drawable.ic_launcher_background, fila.getString(2), fila.getString(3),
                        fila.getString(4), fila.getString(5), fila.getString(6)));

            } while (fila.moveToNext());

        } else {
            Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return itemsAux;
    }
}
