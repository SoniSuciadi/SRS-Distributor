package com.sonisuciadi.simorp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.Adapter.BarangAdapter;
import com.sonisuciadi.simorp.Adapter.DetailsOrderAdapter;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelper;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelperSeles;
import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;
import com.sonisuciadi.simorp.Model.mBarang;
import com.sonisuciadi.simorp.Model.mDetailsOrder;
import com.sonisuciadi.simorp.Response.rBarang;
import com.sonisuciadi.simorp.Response.rOrder;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    RecyclerView rvCart;
    List<mDetailsOrder> mdata;
    DetailsOrderAdapter detailsOrderAdapter;
    TextView tvTotal;
    Button btnPesan;
    Date date = new Date();//Get system time
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    String db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rvCart = findViewById(R.id.rv_cart);
        tvTotal = findViewById(R.id.tv_total);
        Intent i= getIntent();
        db=i.getStringExtra("Activity");



        setRvCart();
        TextView btnBack = findViewById(R.id.btn_back_cart);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, OrderActivity.class);
                intent.putExtra("Activity",getIntent().getStringExtra("Activity"));
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();

            }
        });

        btnPesan=findViewById(R.id.btn_pesan);
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPesanan=insertOrder();
                if (db.equals("Order")){
                    DetailsOrderDataBaseHelper detailsOrderDataBaseHelper=new DetailsOrderDataBaseHelper(CartActivity.this);
                    detailsOrderDataBaseHelper.deleteAllRecord();
                }else {
                    DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelper=new DetailsOrderDataBaseHelperSeles(CartActivity.this);
                    detailsOrderDataBaseHelper.deleteAllRecord();
                }
                btnPesan.setEnabled(false);


            }
        });
    }

    public String insertOrder(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
        String day  = String.valueOf(simpleDateFormatDay.format(date));
        SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("MM");
        String month = String.valueOf(simpleDateFormatMonth.format(date));
        Random rand = new Random();
        Integer pesanan=rand.nextInt((9999 - 1000) + 1) + 1000;
        String idPesanan=day+month+pesanan.toString();
//        Toast.makeText(this, idPesanan, Toast.LENGTH_SHORT).show();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String timeStamp=simpleDateFormat.format(date);
        Toast.makeText(this, timeStamp.toString(), Toast.LENGTH_SHORT).show();
        APIRequestData apiRequestData=RetroServer.konekRetrofit().create(APIRequestData.class);
        Integer idUser=null;
        UsersDataBaseHelper usersDataBaseHelper=new UsersDataBaseHelper(CartActivity.this);
        Cursor userIdentity=usersDataBaseHelper.getUser();
        while (userIdentity.moveToNext()){
            idUser=userIdentity.getInt(0);
        }

        if (db.equals("Order")){
            Call<rOrder> rOrderCall= apiRequestData.InsertOrder(Integer.valueOf(idPesanan),idUser,timeStamp.toString(),detailsOrderAdapter.getTotal(),idUser);
            rOrderCall.enqueue(new Callback<rOrder>() {
                @Override
                public void onResponse(Call<rOrder> call, Response<rOrder> response) {
                    Toast.makeText(CartActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<rOrder> call, Throwable t) {
                    Toast.makeText(CartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            for (int i=0;i<mdata.size();i++){
                Call<rOrder> call=apiRequestData.InsertDetailOrder(Integer.valueOf(idPesanan),mdata.get(i).getId(),mdata.get(i).getHargaBeli(),mdata.get(i).getJumlah());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                    Toast.makeText(CartActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
            Intent intent=new Intent(CartActivity.this,SuccesActivity.class);
            intent.putExtra("id_pesanan",idPesanan);
            startActivity(intent);
        }else {
            Call<rOrder> rOrderCall= apiRequestData.InsertSales(Integer.valueOf(idPesanan),idUser,timeStamp,detailsOrderAdapter.getTotal(),detailsOrderAdapter.getUntung());
            rOrderCall.enqueue(new Callback<rOrder>() {
                @Override
                public void onResponse(Call<rOrder> call, Response<rOrder> response) {
                    Toast.makeText(CartActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<rOrder> call, Throwable t) {
                    Toast.makeText(CartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            for (int i=0;i<mdata.size();i++){
                Call<rOrder> call=apiRequestData.InsertDetailSeles(Integer.valueOf(idPesanan),mdata.get(i).getId(),mdata.get(i).getHargaJual(),mdata.get(i).getJumlah());
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                    Toast.makeText(CartActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(CartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            Intent intent=new Intent(CartActivity.this,SuccesActivity.class);
            intent.putExtra("id_pesanan",idPesanan);
            startActivity(intent);
        }



    return  idPesanan;
    }

    public void confirmDelete(Integer id) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Hapus  Dari Keranjang?")
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (db.equals("Order")){
                            DetailsOrderDataBaseHelper detailsOrderDataBaseHelper = new DetailsOrderDataBaseHelper(CartActivity.this);
                            long deleteItem = detailsOrderDataBaseHelper.deleteDetailOrder(String.valueOf(id));
                            Toast.makeText(CartActivity.this, "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            setRvCart();
                        }else {
                            DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelperSeles = new DetailsOrderDataBaseHelperSeles(CartActivity.this);
                            long deleteItem = detailsOrderDataBaseHelperSeles.deleteDetailOrder(String.valueOf(id));
                            Toast.makeText(CartActivity.this, "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            setRvCart();
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CartActivity.this, "Dibatalkan", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void retriveDetailOrder() {
        mdata = new ArrayList<>();
        DetailsOrderDataBaseHelper detailsOrderDataBaseHelper = new DetailsOrderDataBaseHelper(CartActivity.this);
        Cursor getAll = detailsOrderDataBaseHelper.getDetailOrder();
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);

        if (getAll.getCount() == 0) {
//            mdata=null;
        } else {
            mDetailsOrder detailsOrder=new mDetailsOrder();
            while (getAll.moveToNext()) {
                detailsOrder = new mDetailsOrder();
                detailsOrder.setId(getAll.getInt(0));
                detailsOrder.setHargaBeli(getAll.getInt(1));
                detailsOrder.setStok(getAll.getInt(2));
                detailsOrder.setNama(getAll.getString(3));
                detailsOrder.setJumlah(getAll.getInt(4));
                detailsOrder.setImage(getAll.getString(5));

                mdata.add(detailsOrder);


            }
        }
    }
    public void retriveSeles() {
        mdata = new ArrayList<>();
        DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelperSeles = new DetailsOrderDataBaseHelperSeles(CartActivity.this);
        Cursor getAll = detailsOrderDataBaseHelperSeles.getDetailOrder();
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);

        if (getAll.getCount() == 0) {
//            mdata=null;
        } else {
            mDetailsOrder detailsOrder=new mDetailsOrder();
            while (getAll.moveToNext()) {
                detailsOrder = new mDetailsOrder();
                detailsOrder.setId(getAll.getInt(0));
                detailsOrder.setHargaJual(getAll.getInt(1));
                detailsOrder.setStok(getAll.getInt(2));
                detailsOrder.setNama(getAll.getString(3));
                detailsOrder.setJumlah(getAll.getInt(4));
                detailsOrder.setImage(getAll.getString(5));
                detailsOrder.setHargaBeli(getAll.getInt(6));

                mdata.add(detailsOrder);


            }
        }
    }

    public void setRvCart() {

        if (db.equals("Order")){
            retriveDetailOrder();
        }else {
            retriveSeles();
        }
        if (mdata.size() == 0) {
            Intent intent = new Intent(CartActivity.this, CartNullActivity.class);
            startActivity(intent);
            finish();

        } else {

            final LinearLayoutManager layoutManager = new LinearLayoutManager(CartActivity.this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvCart.setLayoutManager(layoutManager);
            detailsOrderAdapter = new DetailsOrderAdapter(CartActivity.this, mdata, new DetailsOrderAdapter.OnDeleteClickListener() {
                @Override
                public void onItemClick(mDetailsOrder item, int position) {
                    confirmDelete(item.getId());
                }
            }, new DetailsOrderAdapter.OnPlusClickListener() {
                @Override
                public void onPlusClick(mDetailsOrder item, int position) {
                    if (db.equals("Order")){
                        retriveDetailOrder();
                    }else {
                        retriveSeles();
                    }

                    setRvCart();
//                    retriveDetailOrder();
//                    detailsOrderAdapter.notifyDataSetChanged();
                }
            }, new DetailsOrderAdapter.OnMinusClickListener() {
                @Override
                public void onMinusClick(mDetailsOrder item, int position) {
                    if (db.equals("Order")){
                        retriveDetailOrder();
                    }else {
                        retriveSeles();
                    }
                    setRvCart();

//                    detailsOrderAdapter.notifyDataSetChanged();
                }
            },db);

            tvTotal.setText(formatRupiah.format(Double.valueOf(detailsOrderAdapter.getTotal())));
            rvCart.setAdapter(detailsOrderAdapter);
            detailsOrderAdapter.notifyDataSetChanged();
//            }

        }

    }


}