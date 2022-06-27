package com.sonisuciadi.simorp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.Adapter.NotificationAdapter;
import com.sonisuciadi.simorp.Model.mNotification;
import com.sonisuciadi.simorp.Response.rNotification;
import com.sonisuciadi.simorp.Response.rOrder;
import com.sonisuciadi.simorp.Response.rUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {
    RecyclerView rvNotification;
    List<mNotification> mdata;
    FloatingActionButton fabCall;
    NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        rvNotification = findViewById(R.id.rv_notification);
        fabCall = findViewById(R.id.fab_call);
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone=08117858100";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        setRvNotification();
        TextView btnBack = findViewById(R.id.btn_back_alert);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();

    }

    public void setRvNotification() {
        mdata = new ArrayList<>();
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<rNotification> responseModel = apiRequestData.getNotification();
        responseModel.enqueue(new Callback<rNotification>() {
            @Override
            public void onResponse(Call<rNotification> call, Response<rNotification> response) {
                if (response.body().getKode() == 1) {
                    mdata = response.body().getData();
                    notificationAdapter = new NotificationAdapter(NotificationActivity.this, mdata, new NotificationAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(mNotification item, int position) {
                            View dialogView;
                            AlertDialog.Builder dialog;
                            dialog = new AlertDialog.Builder(NotificationActivity.this);
                            dialog.setCancelable(true);
                            dialog.setTitle(item.getJudul());
                            dialog.setMessage(item.getPesan());
                            dialog.show();
                        }
                    });
                    final LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    rvNotification.setLayoutManager(layoutManager);
                    rvNotification.setAdapter(notificationAdapter);
                    notificationAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NotificationActivity.this, "Tidak ada pemberitahuan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<rNotification> call, Throwable t) {

            }
        });
    }
}