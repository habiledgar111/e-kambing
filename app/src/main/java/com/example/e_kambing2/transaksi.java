package com.example.e_kambing2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class transaksi extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://e-kambing-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference dbref = database.getReference();
    ArrayList<model_transaksi> arraymodel = new ArrayList<model_transaksi>();
    String[] bulan = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DES"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);
        ambildata();

        RecyclerView rec = findViewById(R.id.rec_transaksi);
        rec_transaksi_adapter fAdapter= new rec_transaksi_adapter(arraymodel);

        RecyclerView.LayoutManager mlayoutmanager = new LinearLayoutManager(getApplicationContext());
        rec.setLayoutManager(mlayoutmanager);
        rec.setItemAnimator(new DefaultItemAnimator());

        rec.setAdapter(fAdapter);
    }

    private void ambildata() {
        dbref.child("transaksi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    arraymodel.add(child.getValue(model_transaksi.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getApplicationContext(),"gagal mengambil data",Toast.LENGTH_LONG);
            }
        });

    }
}