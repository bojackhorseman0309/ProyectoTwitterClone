package com.example.twitteralonso;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TimeLineActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navHome:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navBuscar:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navNotif:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navMensajes:
                    mTextMessage.setText(R.string.navMensajes);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        List<Tweet> items = new ArrayList<>();

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.rvTimeline);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new TweetAdapter(items);
        recycler.setAdapter(adapter);
    }




}
