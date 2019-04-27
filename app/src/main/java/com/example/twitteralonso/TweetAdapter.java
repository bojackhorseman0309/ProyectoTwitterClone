package com.example.twitteralonso;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.List;


public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder> {

    private List<Tweet> items;
    private static Context contexto;

    public static class TweetViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenPerf;
        public TextView nombre;
        public TextView id;
        public TextView tweet;
        public ImageView imagenFav;
        public TextView contFav;
        public ImageView imagenShare;



        public TweetViewHolder(View v) {
            super(v);
            imagenPerf = (ImageView) v.findViewById(R.id.ivPerfilPerfAct);
            nombre = (TextView) v.findViewById(R.id.tvNomCardPerfAct);
            id = (TextView) v.findViewById(R.id.tvIdPerfCardPerfAct);
            tweet = (TextView) v.findViewById(R.id.tvTuitContCardPerfAct);
            imagenShare = (ImageView) v.findViewById(R.id.ivShareCardPerfAct);

            ((ImageView) v.findViewById(R.id.ivShareCardPerfAct)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent( android.content.Intent.ACTION_SEND);
                    sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sharingIntent.setType("text/plain");
                    String shareBody = tweet.getText().toString();
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    Intent chooserIntent = Intent.createChooser(sharingIntent, "Open With");
                    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    contexto.startActivity(chooserIntent);
                }
            });

        }
    }

    public TweetAdapter(List<Tweet> items, Context context)
    {
        this.items = items;
        this.contexto = context;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tweets_card, viewGroup, false);
        return new TweetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder viewHolder, int i) {
        Bitmap bitmap = items.get(i).getImagenPerf();
        viewHolder.imagenPerf.setImageBitmap(bitmap);
        viewHolder.nombre.setText(items.get(i).getNomUsuario());
        viewHolder.id.setText(items.get(i).getIdNomUsuario());
        viewHolder.tweet.setText(items.get(i).getTweet());
    }



}
