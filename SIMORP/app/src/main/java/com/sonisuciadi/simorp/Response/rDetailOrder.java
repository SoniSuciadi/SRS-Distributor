package com.sonisuciadi.simorp.Response;

import com.sonisuciadi.simorp.Model.mDetailsOrder;

import java.util.List;

public class rDetailOrder {
    int kode;
    String pesan;
    List<mDetailsOrder> data;

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

    public List<mDetailsOrder> getData() {
        return data;
    }

    public void setData(List<mDetailsOrder> data) {
        this.data = data;
    }
}
