package com.example.twitteralonso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetViewHolder>{

    private List<Tweet> items;

    public static class TweetViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
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
            imagenPerf = (ImageView) v.findViewById(R.id.ivPerfil);
            nombre = (TextView) v.findViewById(R.id.tvNomCard);
            id = (TextView) v.findViewById(R.id.tvIdPerfCard);
            tweet = (TextView) v.findViewById(R.id.etTuitContCard);
            imagenRes = (ImageView) v.findViewById(R.id.ivRespondCard);
            contRes = (TextView) v.findViewById(R.id.tvResContCard);
            imagenRetw = (ImageView) v.findViewById(R.id.ivRetuitCard);
            contRetw = (TextView) v.findViewById(R.id.tvRetwContCard);
            imagenFav = (ImageView) v.findViewById(R.id.ivFavCard);
            contFav = (TextView) v.findViewById(R.id.tvFavContCard);
            imagenShare = (ImageView) v.findViewById(R.id.ivShareCard);

            ((ImageView)v.findViewById(R.id.ivPerfil)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView)v.findViewById(R.id.ivRespondCard)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView)v.findViewById(R.id.ivRetuitCard)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView)v.findViewById(R.id.ivFavCard)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageView)v.findViewById(R.id.ivShareCard)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    public TweetAdapter(List<Tweet> items) {
        this.items = items;
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
        viewHolder.imagenPerf.setImageResource(items.get(i).getImagenPerf());
        viewHolder.nombre.setText(items.get(i).getNomUsuario());
        viewHolder.id.setText(items.get(i).getIdNomUsuario());
        viewHolder.tweet.setText(items.get(i).getTweet());
        viewHolder.contRes.setText(items.get(i).getContRes());
        viewHolder.contRetw.setText(items.get(i).getContRetw());
        viewHolder.contFav.setText(items.get(i).getContFav());
    }


}
