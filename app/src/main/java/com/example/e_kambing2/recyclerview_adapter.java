package com.example.e_kambing2;

import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recyclerview_adapter extends RecyclerView.Adapter<recyclerview_adapter.ViewHolder> {
    private ArrayList<model_kambing> listkambing;
    private RecyclerviewInterface listener;
    static FirebaseDatabase database = FirebaseDatabase.getInstance("https://e-kambing-default-rtdb.asia-southeast1.firebasedatabase.app/");
    static DatabaseReference dbref = database.getReference();
    public recyclerview_adapter(ArrayList<model_kambing> listkambing,RecyclerviewInterface listener){
        this.listkambing = listkambing;
        this.listener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvedit,tvhapus,tvrawat,tvjudul;
    ImageView ivkambing;
        public ViewHolder(@NonNull View itemView,RecyclerviewInterface listener) {
            super(itemView);
            //tempat click listener pada item
            tvedit = itemView.findViewById(R.id.edit);
            tvrawat = itemView.findViewById(R.id.rawat);
            tvhapus = itemView.findViewById(R.id.hapus);
            tvjudul = itemView.findViewById(R.id.item_judul);
            ivkambing = itemView.findViewById(R.id.item_photo);

            //detal kambing
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int pos = getBindingAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            listener.onItemClick(pos);
                        }
                    }
                }
            });

            tvedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), edit_kambing.class);
                    v.getContext().startActivity(intent);
                }
            });

            tvhapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //confirmasi untuk hapus
                    dbref.child("kambing").orderByChild("nomer").equalTo(tvjudul.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            //menghapus data
                            for(DataSnapshot data : snapshot.getChildren()){
                                data.getRef().removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            });

            tvrawat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //pindah ke halaman rawat
                }
            });

        }
    }
    @NonNull
    @Override
    public recyclerview_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerview_adapter.ViewHolder holder, int position) {
        holder.tvjudul.setText(listkambing.get(position).getNomer());
//        holder.ivkambing.setImageBitmap();
    }

    @Override
    public int getItemCount() {
        return listkambing.size();}

}
