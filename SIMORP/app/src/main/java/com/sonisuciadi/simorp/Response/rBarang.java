package com.sonisuciadi.simorp.Response;

import com.sonisuciadi.simorp.Model.mBarang;
import com.sonisuciadi.simorp.Model.mUsers;

import java.util.List;

public class rBarang {
    private int kode;
    private String pesan;
    private List<mBarang> data;

    public rBarang() {
        this.kode = kode;
        this.pesan = pesan;
        this.data = data;
    }

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<mBarang> getData() {
        return data;
    }

    public void setData(List<mBarang> data) {
        this.data = data;
    }
}
