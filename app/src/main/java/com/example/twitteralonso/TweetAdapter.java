package com.example.twitteralonso;

import android.content.Context;
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

import java.io.IOException;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder> {

    private List<Tweet> items;
    private Context contexto;

    public static class TweetViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenPerf;
        public TextView nombre;
        public TextView id;
        public TextView tweet;
        public ImageView imagenRes;
        public TextView contRes;
        public ImageView imagenRetw;
        public TextView contRetw;
        public ImageView imagenFav;
        public TextView contFav;
        public ImageView imagenShare;



        public TweetViewHolder(View v) {
            super(v);
            imagenPerf = (ImageView) v.findViewById(R.id.ivPerfilPerfAct);
            nombre = (TextView) v.findViewById(R.id.tvNomCardPerfAct);
            id = (TextView) v.findViewById(R.id.tvIdPerfCardPerfAct);
            tweet = (TextView) v.findViewById(R.id.tvTuitContCardPerfAct);
            imagenRes = (ImageView) v.findViewById(R.id.ivRespondCardPerfAct);
            contRes = (TextView) v.findViewById(R.id.tvResContCard);
            imagenRetw = (ImageView) v.findViewById(R.id.ivRetuitCardPerfAct);
            contRetw = (TextView) v.findViewById(R.id.tvRetwContCard);
            imagenFav = (ImageView) v.findViewById(R.id.ivFavCardPerfAct);
            contFav = (TextView) v.findViewById(R.id.tvFavContCard);
            imagenShare = (ImageView) v.findViewById(R.id.ivShareCardPerfAct);

            ((ImageView) v.findViewById(R.id.ivPerfilPerfAct)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView) v.findViewById(R.id.ivRespondCardPerfAct)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView) v.findViewById(R.id.ivRetuitCardPerfAct)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView) v.findViewById(R.id.ivFavCardPerfAct)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView) v.findViewById(R.id.ivShareCardPerfAct)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
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
        viewHolder.contRes.setText(items.get(i).getContRes());
        viewHolder.contRetw.setText(items.get(i).getContRetw());
        viewHolder.contFav.setText(items.get(i).getContFav());
    }



}
