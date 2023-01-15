package com.example.e_kambing2;

import android.media.TimedText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class rec_transaksi_adapter extends RecyclerView.Adapter<rec_transaksi_adapter.MyVh> {

    ArrayList<model_transaksi> model;

    public class MyVh extends RecyclerView.ViewHolder{
        public TextView judul,tvdate,tvket,Rp;
        public MyVh(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tvjudultransaksi);
            tvdate = itemView.findViewById(R.id.tvitemtransaksidate);
            tvket = itemView.findViewById(R.id.tvkettransaksi);
            Rp = itemView.findViewById(R.id.tvrptransaksi);
        }

        public void bind(String s,String d,String k, String R,String debit){
            judul.setText(s);
            tvdate.setText(d);
            tvket.setText(k);

            if(debit.equalsIgnoreCase("masuk")){
               R = "+"+R;
            }

            if(debit.equalsIgnoreCase("keluar")){
                R = "-"+R;
            }
            Rp.setText(R);

            if(d.isEmpty())tvdate.setVisibility(View.GONE);
        }
    }

    public rec_transaksi_adapter(ArrayList<model_transaksi> model) {
       this.model = model;
    }

    @NonNull
    @Override
    public MyVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaksi_item,parent,false);
        return new MyVh(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVh holder, int position) {
        holder.bind(model.get(position).judul,model.get(position).date,model.get(position).keterangan,model.get(position).harga,model.get(position).debit);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }




}
