package com.sonisuciadi.simorp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;
import com.sonisuciadi.simorp.Model.mDetailsOrder;
import com.sonisuciadi.simorp.Model.mOrder;
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

public class HistoryActivity extends AppCompatActivity {
    List<mOrder> mdata;
    List<mDetailsOrder> mdatadetailorder;
    RecyclerView rvHistory;
    TextView tvDatePicker;
    Button btnSearch;
    SwipeRefreshLayout sRLayout;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        TextView btnBack=findViewById(R.id.btn_back_history);
        tvDatePicker=findViewById(R.id.date_picker);
        Date date = new Date();//Get system time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(date);
        tvDatePicker.setText(nowTime);
        btnSearch=findViewById(R.id.btn_search_history_Order);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRvOrderStatus();
            }
        });
        tvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TampilTanggal();
//                Toast.makeText(HistoryActivity.this, "OKE", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HistoryActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();

            }
        });
        setRvOrderStatus();
    }

    public void TampilTanggal(){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String tahun = ""+datePicker.getYear();
                String bulan = "0"+(datePicker.getMonth()+1);
                String hari = ""+datePicker.getDayOfMonth();
                String text = tahun+"-"+bulan+"-"+hari;
                tvDatePicker.setText(text);
            }
        });
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
    public void setRvOrderStatus(){
        Integer workplaces=Integer.valueOf(getIntent().getStringExtra("branch"));
        rvHistory=findViewById(R.id.rv_history);
//        UsersDataBaseHelper usersDataBaseHelper= new UsersDataBaseHelper(HistoryActivity.this);
//        Cursor userIdentity=usersDataBaseHelper.getUser();
//        while (userIdentity.moveToNext()){
//            workplaces=userIdentity.getInt(4);
//        }
        APIRequestData apiRequestData= RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<rOrder> mOrderCall=apiRequestData.getAllOrder(workplaces,tvDatePicker.getText().toString());
        mOrderCall.enqueue(new Callback<rOrder>() {
            @Override
            public void onResponse(Call<rOrder> call, Response<rOrder> response) {
                mdata=new ArrayList<>();

                if (Integer.valueOf(response.body().getKode())==1){
                    mdata=response.body().getData();

                    SalesOrderAdapter salesOrderAdapter =new SalesOrderAdapter(mdata, HistoryActivity.this, new SalesOrderAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(mOrder item, int position) {
                            showDetailOrder(item.getId(),item.getTanggal(),item.getTotal(),item.getStatus());
                        }
                    });
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(HistoryActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvHistory.setLayoutManager(layoutManager);
                    rvHistory.setAdapter(salesOrderAdapter);
                    salesOrderAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(HistoryActivity.this, "Tidak ada transaksi", Toast.LENGTH_SHORT).show();
                }
                
            }

            @Override
            public void onFailure(Call<rOrder> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(HistoryActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();

    }
    public void showDetailOrder(Integer idPesanan, String tanggalPesanan, Integer total, String status){
        mdatadetailorder=new ArrayList<>();
        View dialogView;
        RecyclerView rvitemorder;
        LayoutInflater inflater;
        AlertDialog.Builder dialog;
        TextView tvIdForm,tvTanggalForm, tvTotalForm, tvStatusForm;
        dialog= new AlertDialog.Builder(HistoryActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_detail_order, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Detail Order");
        rvitemorder=dialogView.findViewById(R.id.rv_detail_order);
        tvIdForm=dialogView.findViewById(R.id.id_pesanan_form);
        tvTanggalForm=dialogView.findViewById(R.id.tanggal_pesanan_form);
        tvTotalForm=dialogView.findViewById(R.id.total_form);
        tvStatusForm=dialogView.findViewById(R.id.status_pesanan_form);
        tvIdForm.setText(String.valueOf(idPesanan));
        tvTanggalForm.setText(tanggalPesanan);
        tvTotalForm.setText(String.valueOf(formatRupiah(Double.valueOf(total))));
        tvStatusForm.setText(status);
        if (status.equals("Not Approve")){
            dialog.setPositiveButton("Konfirmasi Pembayaran", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String url="https://api.whatsapp.com/send?phone=08117858100&text=Saya Mau Konfirmasi Pembayaran Dengan Nomor Order "+idPesanan;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            });
        }
        APIRequestData apiRequestData=RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<rDetailOrder> mDetailsOrderCall= apiRequestData.getDetailOrder(idPesanan);
        mDetailsOrderCall.enqueue(new Callback<rDetailOrder>() {
            @Override
            public void onResponse(Call<rDetailOrder> call, Response<rDetailOrder> response) {
                mdatadetailorder=response.body().getData();
                ItemDetailOrderAdapter itemDetailOrderAdapter=new ItemDetailOrderAdapter(mdatadetailorder, "History");
                final LinearLayoutManager layoutManager = new LinearLayoutManager(dialogView.getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rvitemorder.setLayoutManager(layoutManager);
                rvitemorder.setAdapter(itemDetailOrderAdapter);
                itemDetailOrderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<rDetailOrder> call, Throwable t) {

            }
        });

        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setRvOrderStatus();
            }
        });
    }

}