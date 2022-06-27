package com.sonisuciadi.simorp.Response;

import com.sonisuciadi.simorp.Model.mBarang;
import com.sonisuciadi.simorp.Model.mNotification;

import java.util.List;

public class rNotification {
    private int kode;
    private String pesan;
    private List<mNotification> data;

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

    public List<mNotification> getData() {
        return data;
    }

    public void setData(List<mNotification> data) {
        this.data = data;
    }
}
