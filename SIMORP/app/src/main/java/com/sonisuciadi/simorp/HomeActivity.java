package com.sonisuciadi.simorp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;
import com.sonisuciadi.simorp.Model.mUsers;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    Button btnOrder, btnSales, btnHistory, btnProfile, btnNotification, btnIncome;
    List<mUsers> user = new ArrayList<>();
    TextView username, id, wellcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUserIdentity();

        btnOrder = findViewById(R.id.btn_order);
        btnSales = findViewById(R.id.btn_sales);
        btnNotification = findViewById(R.id.btn_warehouse);
        btnIncome = findViewById(R.id.btn_income);
        btnHistory = findViewById(R.id.btn_history);
        btnProfile = findViewById(R.id.btn_profil);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
                intent.putExtra("Activity", "Order");
                intent.putExtra("userId", user.get(0).getId().toString());
                intent.putExtra("branch", user.get(0).getWorkplaces().toString());

                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
                intent.putExtra("branch", user.get(0).getWorkplaces().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, IncomeActivity.class);
                intent.putExtra("branch", user.get(0).getWorkplaces().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        btnSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
                intent.putExtra("Activity", "Seles");
                intent.putExtra("userId", user.get(0).getId().toString());
                intent.putExtra("branch", user.get(0).getWorkplaces().toString());
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfilActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();
            }
        });

    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void setUserIdentity() {
        UsersDataBaseHelper usersDataBaseHelper = new UsersDataBaseHelper(HomeActivity.this);
        Cursor userIdentity = usersDataBaseHelper.getUser();
        mUsers muser = new mUsers();
        username = findViewById(R.id.tv_namauser);
        id = findViewById(R.id.tv_id_user);
        wellcome = findViewById(R.id.tv_wellcome);

        while (userIdentity.moveToNext()) {
            muser = new mUsers();
            muser.setId(userIdentity.getInt(0));
            muser.setNama(userIdentity.getString(1));
            muser.setAlamat(userIdentity.getString(2));
            muser.setPonsel(userIdentity.getString(3));
            muser.setWorkplaces(userIdentity.getInt(4));
            muser.setJabatan(userIdentity.getString(5));
            muser.setPassword(userIdentity.getString(6));
            muser.setUser_update(userIdentity.getInt(7));
            muser.setUpdate_at(userIdentity.getString(8));
            muser.setNama_cabang(userIdentity.getString(9));
            user.add(muser);
        }
        username.setText(user.get(0).getNama());
        id.setText(user.get(0).getId().toString());
        wellcome.setText(user.get(0).getNama_cabang().toString());
    }
}