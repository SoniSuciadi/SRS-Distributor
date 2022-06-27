package com.sonisuciadi.simorp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.Result;
import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.Adapter.BarangAdapter;
import com.sonisuciadi.simorp.Adapter.ItemDetailOrderAdapter;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelper;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelperSeles;
import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;
import com.sonisuciadi.simorp.Model.mBarang;
import com.sonisuciadi.simorp.Model.mUsers;
import com.sonisuciadi.simorp.Response.rBarang;
import com.sonisuciadi.simorp.Response.rDetailOrder;
import com.sonisuciadi.simorp.Response.rUser;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {

    FloatingActionButton fabScanQR;
    private List<mBarang> dataModels = new ArrayList<>();
    RecyclerView rvBarang;
    BarangAdapter barangAdapter;
    SwipeRefreshLayout sRLayout;
    String activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        if (ContextCompat.checkSelfPermission(OrderActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    50);
        }
        if (ContextCompat.checkSelfPermission(OrderActivity.this, Manifest.permission.INTERNET) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},
                    50);
        }
        Intent i = getIntent();
        activity = i.getStringExtra("Activity");
        TextView header = findViewById(R.id.name);
        header.setText(activity);
        rvBarang = findViewById(R.id.rv_barang);


        setRvBarang();
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
        sRLayout = findViewById(R.id.swlayout);
        sRLayout.setColorSchemeResources(R.color.primaryColor, R.color.secondaryColor);
        sRLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        sRLayout.setRefreshing(false);

                        setRvBarang();
                    }
                }, 5000);
            }
        });

        fabScanQR = findViewById(R.id.fab_scan);
        fabScanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, ScanQRActivity.class);
                intent.putExtra("Activity", activity);
                intent.putExtra("branch", getIntent().getStringExtra("branch"));

                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();

            }

        });
        Button btnSearch = findViewById(R.id.btn_Search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRvBarang();
            }
        });

        TextView btnBack = findViewById(R.id.btn_back_order);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();

            }
        });
        TextView btnCart = findViewById(R.id.btn_cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsOrderDataBaseHelper detailsOrderDataBaseHelper = new DetailsOrderDataBaseHelper(OrderActivity.this);
                Cursor data = detailsOrderDataBaseHelper.getDetailOrder();
                if (data.getCount() == 0) {
                    Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                    intent.putExtra("Activity", activity);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
//                    finish();
                } else {
                    Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                    intent.putExtra("Activity", activity);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
//                    finish();
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();

    }

    public void setRvBarang() {
        dataModels = new ArrayList<>();
        EditText namaBarang = findViewById(R.id.et_Search);
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<rBarang> responseModelCall;
        if (getIntent().getStringExtra("Activity").equals("Order")) {
            responseModelCall = apiRequestData.getDataBarangbyName(77123321, namaBarang.getText().toString());
        } else {
            responseModelCall = apiRequestData.getDataBarangbyName(Integer.parseInt(getIntent().getStringExtra("branch")), namaBarang.getText().toString());
        }

        responseModelCall.enqueue(new Callback<rBarang>() {
            @Override
            public void onResponse(Call<rBarang> call, Response<rBarang> response) {
                dataModels = response.body().getData();
                if (response.body().getKode() == 1) {
//                    Toast.makeText(OrderActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(OrderActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvBarang.setLayoutManager(layoutManager);
                    barangAdapter = new BarangAdapter(dataModels, OrderActivity.this, new BarangAdapter.onAddClickListener() {
                        @Override
                        public void onAddClick(mBarang item, int position) {
                            if (getIntent().getStringExtra("Activity").equals("Order")) {
                                DetailsOrderDataBaseHelper detailsOrderDataBaseHelper = new DetailsOrderDataBaseHelper(OrderActivity.this);
                                long add = detailsOrderDataBaseHelper.insertDetailsOrder(
                                        item.getId(), item.getHargaBeli(), item.getNama(), item.getStok(), item.getImage());
                                if (add != -1) {
                                    Toast.makeText(OrderActivity.this, "Berhasil Ditambah" + item.getNama(), Toast.LENGTH_SHORT).show();

                                } else {
//                        Toast.makeText(context, ""+tvId.getText().toString(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelpers = new DetailsOrderDataBaseHelperSeles(OrderActivity.this);
                                long add = detailsOrderDataBaseHelpers.insertDetailsOrder(
                                        item.getId(), item.getHargaJual(), item.getNama(), item.getStok(), item.getImage(), item.getHargaBeli());
                                if (add != -1) {
                                    Toast.makeText(OrderActivity.this, "Berhasil Ditambah" + item.getNama(), Toast.LENGTH_SHORT).show();

                                } else {
//                        Toast.makeText(context, ""+tvId.getText().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }, new BarangAdapter.onItemClickListener() {
                        @Override
                        public void onItemClick(mBarang item, int position) {
                            showImage(item.getImage(), item.getNama());
                        }
                    }, activity);
                    rvBarang.setAdapter(barangAdapter);
                    barangAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(OrderActivity.this, "Barang Tidak Ditemukan", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<rBarang> call, Throwable t) {
                Toast.makeText(OrderActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showImage(String image, String nama) {
        View dialogView;
        LayoutInflater inflater;
        AlertDialog.Builder dialog;
        TextView tvNama;
        ImageView ivBarang;
        dialog = new AlertDialog.Builder(OrderActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_image_barang, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Detail Gambar");
        tvNama = dialogView.findViewById(R.id.nama_barang_image);
        ivBarang = dialogView.findViewById(R.id.img_form);
        tvNama.setText(nama);
        byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);
        ivBarang.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        dialog.show();

    }


}