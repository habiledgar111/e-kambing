package com.example.e_kambing2;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class model_transaksi implements Serializable {
    String judul,jumlah,harga,keterangan,debit,date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public model_transaksi(String judul, String jumlah, String harga, String keterangan, String debit,String date) {
        this.judul = judul;
        this.jumlah = jumlah;
        this.harga = harga;
        this.keterangan = keterangan;
        this.debit = debit;
        this.date = date;
    }

    public model_transaksi(){}

}
