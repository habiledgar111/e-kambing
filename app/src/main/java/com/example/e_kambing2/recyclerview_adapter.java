package com.example.e_kambing2;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerview_adapter extends RecyclerView.Adapter<recyclerview_adapter.ViewHolder> {
    private ArrayList<model_kambing> listkambing;
    public recyclerview_adapter(ArrayList<model_kambing> listkambing){
        this.listkambing = listkambing;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvedit,tvhapus,tvrawat,tvjudul;
    ImageView ivkambing;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //tempat click listener pada item
            tvedit = itemView.findViewById(R.id.edit);
            tvrawat = itemView.findViewById(R.id.rawat);
            tvhapus = itemView.findViewById(R.id.hapus);
            tvjudul = itemView.findViewById(R.id.item_judul);
            ivkambing = itemView.findViewById(R.id.item_photo);

        }
    }
    @NonNull
    @Override
    public recyclerview_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerview_adapter.ViewHolder holder, int position) {
        holder.tvjudul.setText(listkambing.get(position).getJudul());
    }

    @Override
    public int getItemCount() {
        return listkambing.size();}
}
