package com.sonisuciadi.simorp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.Adapter.ItemDetailOrderAdapter;
import com.sonisuciadi.simorp.Adapter.SalesOrderAdapter;
import com.sonisuciadi.simorp.Model.mDetailsOrder;
import com.sonisuciadi.simorp.Model.mOrder;
import com.sonisuciadi.simorp.Response.rBarang;
import com.sonisuciadi.simorp.Response.rDetailOrder;
import com.sonisuciadi.simorp.Response.rOrder;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeActivity extends AppCompatActivity {
    TextView tvDatePicker;
    Button btnSearch;
    RecyclerView rvIncome;
    List<mOrder> mdata;
    List<mDetailsOrder> mdatadetailorder;
    Date date = new Date();//Get system time
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        TextView btnBack = findViewById(R.id.btn_back_income);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IncomeActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        tvDatePicker = findViewById(R.id.date_picker);

        String nowTime = sdf.format(date);
        tvDatePicker.setText(nowTime);
        tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TampilTanggal();
//                Toast.makeText(HistoryActivity.this, "OKE", Toast.LENGTH_SHORT).show();
            }
        });
        btnSearch = findViewById(R.id.btn_search_income);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRvIncome();
            }
        });
        setRvIncome();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(IncomeActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();

    }

    public void TampilTanggal() {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String tahun = "" + datePicker.getYear();
                String bulan = "0" + (datePicker.getMonth() + 1);
                String hari = "" + datePicker.getDayOfMonth();
                String text = tahun + "-" + bulan + "-" + hari;
                tvDatePicker.setText(text);
            }
        });
    }

    public void setRvIncome() {
        int branch = Integer.valueOf(getIntent().getStringExtra("branch"));
        rvIncome = findViewById(R.id.rv_income);
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<rOrder> mOrderCall = apiRequestData.getAllSeles(branch, tvDatePicker.getText().toString());
        mOrderCall.enqueue(new Callback<rOrder>() {
            @Override
            public void onResponse(Call<rOrder> call, Response<rOrder> response) {
                int subtot = 0;
                mdata = new ArrayList<>();
                if (response.body().getKode() == 1) {
                    mdata = response.body().getData();
                    SalesOrderAdapter salesOrderAdapter = new SalesOrderAdapter(mdata, IncomeActivity.this, new SalesOrderAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(mOrder item, int position) {
                            showDetailOrder(item.getId(), item.getTanggal(), item.getTotal(), item.getStatus());
                        }
                    });
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(IncomeActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvIncome.setLayoutManager(layoutManager);
                    rvIncome.setAdapter(salesOrderAdapter);
                    salesOrderAdapter.notifyDataSetChanged();
                    for (int i = 0; i < mdata.size(); i++) {
                        if (mdata.get(i).getStatus().toString().equals("Selesai")) {
                            subtot += mdata.get(i).getTotal();
                        }
                    }
                    TextView total = findViewById(R.id.tv_total);
                    total.setText(formatRupiah((double) subtot));
                } else {
                    Toast.makeText(IncomeActivity.this, "Tidak Ada Transaksi", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<rOrder> call, Throwable t) {

            }
        });
    }

    private String formatRupiah(Double number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

    public void showDetailOrder(Integer idPesanan, String tanggalPesanan, Integer total, String status) {
        mdatadetailorder = new ArrayList<>();
        View dialogView;
        RecyclerView rvitemorder;
        LayoutInflater inflater;
        AlertDialog.Builder dialog;
        TextView tvIdForm, tvTanggalForm, tvTotalForm, tvStatusForm;
        dialog = new AlertDialog.Builder(IncomeActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_detail_order, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Detail Order");
        rvitemorder = dialogView.findViewById(R.id.rv_detail_order);
        tvIdForm = dialogView.findViewById(R.id.id_pesanan_form);
        tvTanggalForm = dialogView.findViewById(R.id.tanggal_pesanan_form);
        tvTotalForm = dialogView.findViewById(R.id.total_form);
        tvStatusForm = dialogView.findViewById(R.id.status_pesanan_form);
        tvIdForm.setText(String.valueOf(idPesanan));
        tvTanggalForm.setText(tanggalPesanan);
        tvTotalForm.setText(String.valueOf(formatRupiah(Double.valueOf(total))));
        tvStatusForm.setText(status);
        Integer idbranch = Integer.valueOf(getIntent().getStringExtra("branch"));
        Toast.makeText(this, idbranch.toString(), Toast.LENGTH_SHORT).show();
        if (status.equals("Selesai") && tanggalPesanan.equals(sdf.format(date).toString())) {
            dialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                    Call<rBarang> koreksi = apiRequestData.koreksiSales(Integer.valueOf(idPesanan));
                    koreksi.enqueue(new Callback<rBarang>() {
                        @Override
                        public void onResponse(Call<rBarang> call, Response<rBarang> response) {
                            tvStatusForm.setText("Cancel");
                            Toast.makeText(IncomeActivity.this, idPesanan.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<rBarang> call, Throwable t) {

                        }
                    });
                    for (int j = 0; j < mdata.size(); j++) {
                        Call revers = apiRequestData.reversStok(idbranch, mdatadetailorder.get(j).getId(), mdatadetailorder.get(j).getJumlah());
                        revers.enqueue(new Callback<rBarang>() {
                            @Override
                            public void onResponse(Call<rBarang> call, Response<rBarang> response) {
                                Toast.makeText(IncomeActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<rBarang> call, Throwable t) {
                                Toast.makeText(IncomeActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    setRvIncome();
                }
            });
        }
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<rDetailOrder> mDetailsOrderCall = apiRequestData.getDetailSales(idPesanan);
        mDetailsOrderCall.enqueue(new Callback<rDetailOrder>() {
            @Override
            public void onResponse(Call<rDetailOrder> call, Response<rDetailOrder> response) {
                mdatadetailorder = response.body().getData();
                if (mdatadetailorder.size() > 0) {
                    ItemDetailOrderAdapter itemDetailOrderAdapter = new ItemDetailOrderAdapter(mdatadetailorder, "Income");
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(dialogView.getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvitemorder.setLayoutManager(layoutManager);
                    rvitemorder.setAdapter(itemDetailOrderAdapter);
                    itemDetailOrderAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(IncomeActivity.this, "Galat", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(IncomeActivity.this, mdatadetailorder.get(0).getNama(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<rDetailOrder> call, Throwable t) {

            }
        });

        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setRvIncome();
            }
        });
    }
}