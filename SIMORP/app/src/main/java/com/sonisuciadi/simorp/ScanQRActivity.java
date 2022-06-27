package com.sonisuciadi.simorp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelper;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelperSeles;
import com.sonisuciadi.simorp.Model.mBarang;
import com.sonisuciadi.simorp.Response.rBarang;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanQRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_q_r);
        if (ContextCompat.checkSelfPermission(ScanQRActivity.this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    50);
        }
//        mScannerView = new ZXingScannerView(this);
//        setContentView(mScannerView);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.appBar_scannerr);
        mScannerView = new ZXingScannerView(this);
        contentFrame.addView(mScannerView);
        TextView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanQRActivity.this, OrderActivity.class);
                intent.putExtra("Activity", getIntent().getStringExtra("Activity"));
                intent.putExtra("branch", getIntent().getStringExtra("branch"));

                startActivity(intent);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");

        try {
            Integer idBarang = Integer.valueOf(rawResult.getText());
            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<rBarang> responseModelCall = apiRequestData.getDataBarangbyId(Integer.parseInt(getIntent().getStringExtra("branch")), Integer.valueOf(rawResult.getText()));
            responseModelCall.enqueue(new Callback<rBarang>() {
                @Override
                public void onResponse(Call<rBarang> call, Response<rBarang> response) {
                    DetailsOrderDataBaseHelper detailsOrderDataBaseHelper = new DetailsOrderDataBaseHelper(ScanQRActivity.this);
                    DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelperSeles = new DetailsOrderDataBaseHelperSeles(ScanQRActivity.this);
                    List<mBarang> mBarangs = response.body().getData();
                    long add = 0;
                    if (mBarangs.get(0).getStok() > 0) {
                        if (getIntent().getStringExtra("Activity").equals("Order")) {
                            add = detailsOrderDataBaseHelper.insertDetailsOrder(mBarangs.get(0).getId(), mBarangs.get(0).getHargaBeli(), mBarangs.get(0).getNama(), mBarangs.get(0).getStok(), mBarangs.get(0).getImage());
                        } else {
                            add = detailsOrderDataBaseHelperSeles.insertDetailsOrder(mBarangs.get(0).getId(), mBarangs.get(0).getHargaJual(), mBarangs.get(0).getNama(), mBarangs.get(0).getStok(), mBarangs.get(0).getImage(), mBarangs.get(0).getHargaBeli());
                        }

                        if (add != -1) {
                            builder.setTitle("Alert");
                            builder.setMessage("Berhasil Ditambah");
                            builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(ScanQRActivity.this, CartActivity.class);
                                    intent.putExtra("Activity", getIntent().getStringExtra("Activity"));
                                    mScannerView.stopCamera();
                                    ScanQRActivity.this.finish();
                                    startActivity(intent);

                                }
                            });
                            AlertDialog alert1 = builder.create();
                            alert1.show();

//                    Toast.makeText(ScanQRActivity.this, "", Toast.LENGTH_SHORT).show();

                        } else {
                            builder.setMessage("Barang Sudah Ada");
                            builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(ScanQRActivity.this, OrderActivity.class);
                                    intent.putExtra("Activity", getIntent().getStringExtra("Activity"));
                                    mScannerView.stopCamera();
                                    ScanQRActivity.this.finish();
                                    startActivity(intent);

                                }
                            });
                            builder.setNegativeButton("Scan Barang Lain", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    mScannerView.resumeCameraPreview(ScanQRActivity.this);
                                }
                            });
//                    Toast.makeText(ScanQRActivity.this,"Barang Sudah Ada", Toast.LENGTH_SHORT).show();
                            AlertDialog alert1 = builder.create();
                            alert1.show();
                        }
                    } else {
                        builder.setMessage("Stok Barang Kosong");
                        builder.setNegativeButton("OKE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mScannerView.resumeCameraPreview(ScanQRActivity.this);
                            }
                        });
                        AlertDialog alert1 = builder.create();
                        alert1.show();
                    }

                }

                @Override
                public void onFailure(Call<rBarang> call, Throwable t) {
                    builder.setNegativeButton("Scan Barang Lain", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mScannerView.resumeCameraPreview(ScanQRActivity.this);
                        }
                    });
                    AlertDialog alert1 = builder.create();
                    alert1.show();
//                Toast.makeText(ScanQRActivity.this,"Barcode Tidak Dimengerti", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            builder.setMessage("Barcode Tidak Dikenali");
            builder.setNegativeButton("Scan Barang Lain", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mScannerView.resumeCameraPreview(ScanQRActivity.this);
                }
            });
            AlertDialog alert1 = builder.create();
            alert1.show();
        }


//        mScannerView.resumeCameraPreview(this);
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)  {
//        if (keyCode == KeyEvent.KEYCODE_BACK ) {
//            Intent intent = new Intent(ScanQRActivity.this,OrderActivity.class);
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
}