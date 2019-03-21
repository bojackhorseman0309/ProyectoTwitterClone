package com.example.twitteralonso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MensajesActivity extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        List<Mensaje> items = new ArrayList<>();

        items.add(new Mensaje(R.drawable.ic_launcher_background, "Bojack", "@bojack", "20 de mar", "Hola estupido"));

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
}
