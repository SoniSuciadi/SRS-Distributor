package com.sonisuciadi.simorp.Response;

import com.sonisuciadi.simorp.Model.mOrder;

import java.util.List;

public class rOrder {
    int kode;
    String pesan;
    List<mOrder> data;

    public List<mOrder> getData() {
        return data;
    }

    public void setData(List<mOrder> data) {
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
}
