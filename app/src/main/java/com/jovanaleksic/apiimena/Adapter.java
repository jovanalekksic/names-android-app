package com.jovanaleksic.apiimena;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Stavka> lista;

    public Adapter(ArrayList<Stavka> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Stavka stavka = lista.get(position);
        holder.slika.setImageResource(stavka.getSlika());
        holder.textView1.setText(stavka.getIme());
        holder.textView.setText(stavka.getState());

        holder.slika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position!=RecyclerView.NO_POSITION){
                    lista.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(holder.getAdapterPosition(),getItemCount());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView slika;
        TextView textView;
        TextView textView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            slika = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView1 = itemView.findViewById(R.id.textView2);
        }
    }
}