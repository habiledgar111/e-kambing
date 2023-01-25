package com.example.e_kambing2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class tambah_kambing extends AppCompatActivity {
    private Button btDate,tambah_kambing;
    private DatePickerDialog datepick;
    private EditText nomer,warna,kelamin,harga;
    String nomer_k,warna_k,kelamin_k,harga_k,id_k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kambing);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://e-kambing-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference dbref = database.getReference();
        btDate = findViewById(R.id.btDatekambing);
        tambah_kambing = findViewById(R.id.btTambahkambing);
        nomer = findViewById(R.id.etNamakambing);
        warna = findViewById(R.id.etWarnakambing);
        kelamin = findViewById(R.id.etWarnakambing);
        harga = findViewById(R.id.etHargakambing);
        initdatepicker();
        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepick.show();
            }
        });
        tambah_kambing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomer_k = nomer.getText().toString();
                warna_k = warna.getText().toString();
                kelamin_k = kelamin.getText().toString();
                harga_k = harga.getText().toString();
                model_kambing model = new model_kambing(nomer_k,warna_k,kelamin_k,harga_k);
                dbref.child("kambing").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast toast1 = Toast.makeText(getApplicationContext(),model.nomer,Toast.LENGTH_LONG);
                        toast1.show();
//                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast2 = Toast.makeText(getApplicationContext(),"gagal masukan data",Toast.LENGTH_LONG);
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
                btDate.setText(tanggal);
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