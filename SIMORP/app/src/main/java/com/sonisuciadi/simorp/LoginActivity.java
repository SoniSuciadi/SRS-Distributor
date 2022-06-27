package com.sonisuciadi.simorp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;
import com.sonisuciadi.simorp.Model.mUsers;
import com.sonisuciadi.simorp.Response.rUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin,btnReset;
    Button btnBack;
    private List<mUsers> dataModels=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_NETWORK_STATE},
                    50); }
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.INTERNET) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET},
                    50); }
//        retriveDataUser();
        btnLogin=findViewById(R.id.btn_login);
        EditText username=findViewById(R.id.et_username);
        EditText password=findViewById(R.id.et_password);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                System.out.println(dataModels);


                APIRequestData apiRequestData= RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<rUser> login=apiRequestData.login(username.getText().toString(),password.getText().toString());
                login.enqueue(new Callback<rUser>() {
                    @Override
                    public void onResponse(Call<rUser> call, Response<rUser> response) {
                        if (response.body().getKode()==1){
                            List<mUsers> user=response.body().getData();
                            if (user.get(0).getJabatan().equals("Penjualan")){
                                Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
                                UsersDataBaseHelper usersDataBaseHelper=new UsersDataBaseHelper(LoginActivity.this);
                                long val =usersDataBaseHelper.insertUser(user);
                                startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, "Maaf Anda Tidak Memiliki Hak Akses", Toast.LENGTH_SHORT).show();
                            }


                        }else {
                            Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<rUser> call, Throwable t) {

                    }
                });
            }
        });

    }


//                boolean loginStatus=false;
//                for (Integer i=0;i<dataModels.size();i++){
//                    if (dataModels.get(i).getNama().toString().equals(username.getText().toString())&&
//                    dataModels.get(i).getPassword().toString().equals(password.getText().toString())){
//                        loginStatus=true;
//                        Intent intent=new Intent(LoginActivity.this, HomeActivity.class);
//                        intent.putExtra("ID",dataModels.get(i).getId().toString());
//                        intent.putExtra("NAMA",dataModels.get(i).getNama());
//                        Toast.makeText(LoginActivity.this, "Selamat Datang "+username.getText().toString(), Toast.LENGTH_SHORT).show();
//                        startActivity(intent);
//
//                        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
//                        finish();
//                    }
//                }
//                if (loginStatus==false){
//                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
//                }





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
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    public void retriveDataUser(){
        APIRequestData apiRequestData= RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<rUser> responseModelCall=apiRequestData.getDataUsers();
        responseModelCall.enqueue(new Callback<rUser>() {
            @Override
            public void onResponse(Call<rUser> call, Response<rUser> response) {
                int kode=response.body().getKode();
                String pesan=response.body().getPesan();
                dataModels=response.body().getData();
//                Toast.makeText(LoginActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<rUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}