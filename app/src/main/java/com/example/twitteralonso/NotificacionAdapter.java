package com.example.twitteralonso;

import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NotificacionAdapter extends RecyclerView.Adapter<NotificacionAdapter.NotifViewHolder> {

    private List<Notificacion> items;

    public static class NotifViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenNotif;
        public TextView notificacion;
        public TextView descripcion;


        public NotifViewHolder(View v) {
            super(v);
            imagenNotif = (ImageView) v.findViewById(R.id.ivPerfNotif);
            notificacion = (TextView) v.findViewById(R.id.tvNotif);
            descripcion = (TextView) v.findViewById(R.id.tvTweetNotif);
        }
    }

    public NotificacionAdapter(List<Notificacion> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public NotificacionAdapter.NotifViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notificaciones_card, viewGroup, false);
        return new NotificacionAdapter.NotifViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificacionAdapter.NotifViewHolder viewHolder, int i) {
        Bitmap bitmap = items.get(i).getImagenNotif();
        viewHolder.imagenNotif.setImageBitmap(bitmap);
        viewHolder.notificacion.setText(items.get(i).getNotif());
        viewHolder.descripcion.setText(items.get(i).getDesc());
    }

}
