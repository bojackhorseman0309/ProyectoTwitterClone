package com.example.twitteralonso;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MensajeViewHolder>{

    private List<Mensaje> items;

    public static class MensajeViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenMens;
        public TextView nomUsuario;
        public TextView idUsuario;
        public TextView fecha;
        public TextView mensaje;


        public MensajeViewHolder(View v) {
            super(v);
            imagenMens = (ImageView) v.findViewById(R.id.ivPerfilMens);
            nomUsuario = (TextView) v.findViewById(R.id.tvNomMens);
            idUsuario = (TextView) v.findViewById(R.id.tvIdMens);
            fecha = (TextView) v.findViewById(R.id.tvFechaMens);
            mensaje = (TextView) v.findViewById(R.id.tvMensajeMens);

            ((CardView) v.findViewById(R.id.mensajeCard)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Aca esta", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public MensajeAdapter(List<Mensaje> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MensajeAdapter.MensajeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mensaje_card, viewGroup, false);
        return new MensajeAdapter.MensajeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MensajeAdapter.MensajeViewHolder viewHolder, int i) {
        viewHolder.imagenMens.setImageResource(items.get(i).getImagenPerf());
        viewHolder.nomUsuario.setText(items.get(i).getNomUsuario());
        viewHolder.idUsuario.setText(items.get(i).getIdUsuario());
        viewHolder.fecha.setText(items.get(i).getFecha());
        viewHolder.mensaje.setText(items.get(i).getMensaje());
    }
}
