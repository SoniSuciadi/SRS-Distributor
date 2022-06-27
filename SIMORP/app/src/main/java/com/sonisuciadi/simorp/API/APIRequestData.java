package com.sonisuciadi.simorp.API;
import android.text.Editable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import com.sonisuciadi.simorp.Model.mUsers;
import com.sonisuciadi.simorp.Response.rBarang;
import com.sonisuciadi.simorp.Response.rDetailOrder;
import com.sonisuciadi.simorp.Response.rNotification;
import com.sonisuciadi.simorp.Response.rOrder;
import com.sonisuciadi.simorp.Response.rUser;

public interface APIRequestData {
    @GET("getAllUsers.php")
    Call<rUser> getDataUsers();

    @GET("getAllNotification.php")
    Call<rNotification> getNotification();

    @FormUrlEncoded
    @POST("getBarangbyName.php")
    Call<rBarang> getDataBarangbyName(
            @Field("id_branch") Integer id_branch,
            @Field("nama") String nama
    );

    @FormUrlEncoded
    @POST("getBarangbyId.php")
    Call<rBarang> getDataBarangbyId(
            @Field("id_branch") Integer id_branch,
            @Field("id") Integer id

    );

    @FormUrlEncoded
    @POST("koreksiSales.php")
    Call<rBarang> koreksiSales(
            @Field("idsales") Integer idsales

    );
    @FormUrlEncoded
    @POST("reverseStok.php")
    Call<rBarang> reversStok(
            @Field("idbranch") Integer idbranch,
            @Field("idbarang") Integer idbarang,
            @Field("jumlah") Integer jumlah
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<rUser> login(
            @Field("nama") String nama,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("insertOrder.php")
    Call<rOrder> InsertOrder(
            @Field("id") Integer id,
            @Field("id_pengguna") Integer id_pengguna,
            @Field("tanggal_pesanan") String tanggal_pesanan,
            @Field("total") Integer total,
            @Field("user_approve") Integer user_approve
    );
    @FormUrlEncoded
    @POST("insertDetailOrder.php")
    Call<rOrder> InsertDetailOrder(
            @Field("id_order") Integer id_order,
            @Field("id_barang") Integer id_barang,
            @Field("harga") Integer harga,
            @Field("jumlah") Integer jumlah
    );

    @FormUrlEncoded
    @POST("insertSales.php")
    Call<rOrder> InsertSales(
            @Field("id") Integer id,
            @Field("id_pengguna") Integer id_pengguna,
            @Field("tanggal_seles") String tanggal_seles,
            @Field("total") Integer total,
            @Field("keuntungan") Integer keuntungan

    );
    @FormUrlEncoded
    @POST("insertDetailSeles.php")
    Call<rOrder> InsertDetailSeles(
            @Field("id_seles") Integer id_seles,
            @Field("id_barang") Integer id_barang,
            @Field("harga") Integer harga,
            @Field("jumlah") Integer jumlah
    );

    @FormUrlEncoded
    @POST("getAllOrder.php")
    Call<rOrder> getAllOrder(
            @Field("id_branch") Integer id_branch,
            @Field("tanggal") String tanggal
    );
    @FormUrlEncoded
    @POST("getDetailOrderbyIdOrder.php")
    Call<rDetailOrder> getDetailOrder(
            @Field("id_order") Integer id_order
    );
    @FormUrlEncoded
    @POST("getDetailOrderbyIdSales.php")
    Call<rDetailOrder> getDetailSales(
            @Field("id_sales") Integer id_sales
    );

    @FormUrlEncoded
    @POST("getAllSales.php")
    Call<rOrder> getAllSeles(
            @Field("id_branch") Integer id_branch,
            @Field("tanggal") String tanggal
    );

    @FormUrlEncoded
    @POST("updateUser.php")
    Call<rUser> updateUser(
            @Field("alamat") String alamat,
            @Field("ponsel") String ponsel,
            @Field("password") String password,
            @Field("id") Integer id

    );

}
