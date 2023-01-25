package com.example.e_kambing2;

import static java.util.stream.Collectors.toMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import kotlin.collections.ArrayDeque;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://e-kambing-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference dbref = database.getReference();
    ArrayList<model_kambing> arraykambing = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rec = findViewById(R.id.recycler_main);
        Button tambah_transaksi = findViewById(R.id.bttambahtransaksi);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
//        ArrayList<model_kambing> listdata = new ArrayList<>();
//        listdata.add(new model_kambing("kambing 000"));
//        listdata.add(new model_kambing("kambing 001"));
//        listdata.add(new model_kambing("kambing 002"));
        ambildata();
        recyclerview_adapter adapter = new recyclerview_adapter(arraykambing);
        rec.setAdapter(adapter);

        ImageView goat,home,transaksi;

        goat = findViewById(R.id.button_goat);
        home = findViewById(R.id.button_home);
        transaksi = findViewById(R.id.button_transaksi);

        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),transaksi.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), tambah_kambing.class);
                startActivity(intent);
            }
        });

        tambah_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), tambah_transaksi.class);
                startActivity(intent);
            }
        });
    }

    private void ambildata() {
        dbref.child("kambing").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    arraykambing.add(child.getValue(model_kambing.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getApplicationContext(),"gagal mengambil data",Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
}