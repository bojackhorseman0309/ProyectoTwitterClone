package com.example.twitteralonso;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
        setContentView(R.layout.activity_mensajes);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavMensajes);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().findItem(R.id.navMensajes).setChecked(true);
        session = new Session(getApplicationContext());
        data = new TwitterDB(this, "datos", null, 1);

        List<Mensaje> items = consultaMensaje();

        recycler = (RecyclerView) findViewById(R.id.rvMensajes);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

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
        Cursor fila = conn.rawQuery("SELECT * FROM mensaje WHERE origen = '" + session.getNomUsuario().trim() + "'", null);
        if (fila.moveToFirst()) {
            do {
                Bitmap bitmap = getImage(fila.getBlob(1));
                itemsAux.add(new Mensaje(bitmap, fila.getString(2), fila.getString(3),
                        fila.getString(4), fila.getString(5), fila.getString(6)));
            } while (fila.moveToNext());
        } else {
            //Toast.makeText(this, R.string.toastNoHayRegistros, Toast.LENGTH_SHORT).show();
        }
        conn.close();
        return itemsAux;
    }

    public Bitmap getImage(byte[] imag){

            return BitmapFactory.decodeByteArray(imag, 0, imag.length);

    }
}
