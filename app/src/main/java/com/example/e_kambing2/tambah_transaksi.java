package com.example.e_kambing2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class tambah_transaksi extends AppCompatActivity {

    private String j_transaksi,k_transaksi,jum_transaksi,h_transaksi,d_transaksi,debit;
    private DatePickerDialog datepick;
    private Button date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi);
        initdatepicker();
        Spinner dropdown = (Spinner) findViewById(R.id.drop_transaksi);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://e-kambing-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference dbref = database.getReference();
        Button bt_tambah = findViewById(R.id.bttambahtransaksi);
        EditText judul,keterangan,jumlah,harga;
        judul = findViewById(R.id.etjudultransaksi);
        keterangan = findViewById(R.id.etkettransaksi);
        jumlah = findViewById(R.id.etjumlahtransaksi);
        harga = findViewById(R.id.etHargatransaksi);
        date = findViewById(R.id.btDatetransaksi);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick.show();
            }
        });
        bt_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  j_transaksi = judul.getText().toString();
                  k_transaksi = keterangan.getText().toString();
                  jum_transaksi = jumlah.getText().toString();
                  h_transaksi = harga.getText().toString();
                  k_transaksi = keterangan.getText().toString();
                  debit = dropdown.getSelectedItem().toString();

                  model_transaksi model = new model_transaksi(j_transaksi,jum_transaksi,h_transaksi,k_transaksi,debit,date.getText().toString());
                  dbref.child("transaksi").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void unused) {
                          Toast toast1 = Toast.makeText(getApplicationContext(),model.debit,Toast.LENGTH_LONG);
                          toast1.show();
                          finish();
                      }
                  }).addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          Toast toast2 = Toast.makeText(getApplicationContext(),"gagal menyimpan data",Toast.LENGTH_LONG);
                      }
                  });
            }
        });
    }

    private void initdatepicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String tanggal = makedateString(dayOfMonth,month,year);
                date.setText(tanggal);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datepick = new DatePickerDialog(this, com.google.android.material.R.style.Theme_Material3_Dark_Dialog_Alert,dateSetListener,year,month,day);
    }

    private String makedateString(int dayOfMonth, int month, int year) {
        return dayOfMonth+" "+getmonthformat(month)+" "+year;
    }

    private String getmonthformat(int month) {
        if(month == 1 ) return "JAN";
        if(month == 2 ) return "FEB";
        if(month == 3 ) return "MAR";
        if(month == 4 ) return "APR";
        if(month == 5 ) return "MAY";
        if(month == 6 ) return "JUN";
        if(month == 7 ) return "JUL";
        if(month == 8 ) return "AUG";
        if(month == 9 ) return "SEP";
        if(month == 10 ) return "OCT";
        if(month == 11 ) return "NOV";
        if(month == 12 ) return "DEC";

        return  "JAN";
    }
}