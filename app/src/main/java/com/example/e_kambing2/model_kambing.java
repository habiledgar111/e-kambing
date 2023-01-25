package com.example.e_kambing2;

import android.widget.ImageView;

import java.io.Serializable;

public class model_kambing implements Serializable {
    String nomer,warna,kelamin,harga,photo,id;

    public model_kambing() {
    }


    public model_kambing(String nomer, String warna, String kelamin, String harga) {
        this.nomer = nomer;
        this.warna = warna;
        this.kelamin = kelamin;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public model_kambing(String nomer, String warna, String kelamin, String harga, String photo, String id) {
        this.nomer = nomer;
        this.warna = warna;
        this.kelamin = kelamin;
        this.harga = harga;
        this.photo = photo;
        this.id = id;
    }

    public model_kambing(String nomer, String warna, String kelamin, String harga, String photo) {
        this.nomer = nomer;
        this.warna = warna;
        this.kelamin = kelamin;
        this.harga = harga;
        this.photo = photo;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
