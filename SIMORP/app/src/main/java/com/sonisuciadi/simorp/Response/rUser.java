package com.sonisuciadi.simorp.Response;

import com.sonisuciadi.simorp.Model.mUsers;

import java.util.List;

public class rUser {
    private int kode;
    private String pesan;
    private List<mUsers> data;

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

    public List<mUsers> getData() {
        return data;
    }

    public void setData(List<mUsers> data) {
        this.data = data;
    }
}
