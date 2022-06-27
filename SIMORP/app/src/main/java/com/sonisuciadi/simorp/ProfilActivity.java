package com.sonisuciadi.simorp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sonisuciadi.simorp.API.APIRequestData;
import com.sonisuciadi.simorp.API.RetroServer;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelper;
import com.sonisuciadi.simorp.DataBaseHelper.DetailsOrderDataBaseHelperSeles;
import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;
import com.sonisuciadi.simorp.Model.mUsers;
import com.sonisuciadi.simorp.Response.rDetailOrder;
import com.sonisuciadi.simorp.Response.rUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    List<mUsers> user = new ArrayList<>();
    EditText nohp, alamat, posisi, cabang;
    TextView nama, id;
    Button btnGanti, btnSimpan, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        nama = findViewById(R.id.tv_nama_profil);
        id = findViewById(R.id.tv_id_profil);
        nohp = findViewById(R.id.et_nohp);
        alamat = findViewById(R.id.et_alamat);
        posisi = findViewById(R.id.et_posisi);
        cabang = findViewById(R.id.et_cabang);

        TextView btnBack = findViewById(R.id.btn_back_profil);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();

            }
        });
        setUserIdentity();
        btnGanti = findViewById(R.id.btn_ganti_password);
        btnGanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGantiPassword();
            }
        });
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSimpanDialog();
            }
        });
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(ProfilActivity.this);
                dialog.setCancelable(true);
                dialog.setTitle("Keluar");
                dialog.setMessage("Yakin Keluar ?");
                dialog.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UsersDataBaseHelper usersDataBaseHelper = new UsersDataBaseHelper(ProfilActivity.this);
                        usersDataBaseHelper.deleteUser(user.get(0).getId().toString());
                        DetailsOrderDataBaseHelper detailsOrderDataBaseHelper = new DetailsOrderDataBaseHelper(ProfilActivity.this);
                        detailsOrderDataBaseHelper.deleteAllRecord();
                        DetailsOrderDataBaseHelperSeles detailsOrderDataBaseHelperSeles = new DetailsOrderDataBaseHelperSeles(ProfilActivity.this);
                        detailsOrderDataBaseHelperSeles.deleteAllRecord();
                        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                        finish();
                    }
                });
                dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfilActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        finish();

    }

    public void setUserIdentity() {
        UsersDataBaseHelper usersDataBaseHelper = new UsersDataBaseHelper(ProfilActivity.this);
        Cursor userIdentity = usersDataBaseHelper.getUser();
        mUsers muser = new mUsers();


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
        nama.setText(user.get(0).getNama());
        id.setText(user.get(0).getId().toString());
        nohp.setText(user.get(0).getPonsel());
        alamat.setText(user.get(0).getAlamat());
        posisi.setText(user.get(0).getJabatan());
        cabang.setText(user.get(0).getNama_cabang());


    }

    public void showGantiPassword() {
        View dialogView;
        LayoutInflater inflater;
        AlertDialog.Builder dialog;
        EditText passwordSekarang, passwordBaru, passwordConfirm;
        dialog = new AlertDialog.Builder(ProfilActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_ganti_password, null);
        passwordSekarang = dialogView.findViewById(R.id.et_password);
        passwordBaru = dialogView.findViewById(R.id.et_password_baru);
        passwordConfirm = dialogView.findViewById(R.id.et_password_baru_confim);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Ganti Password");
        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (passwordSekarang.getText().toString().equals(user.get(0).getPassword()) && passwordBaru.getText().toString().equals(passwordConfirm.getText().toString())) {
                    APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                    Call<rUser> updateUser = apiRequestData.updateUser(alamat.getText().toString(), nohp.getText().toString(), passwordBaru.getText().toString(), Integer.valueOf(id.getText().toString()));
                    updateUser.enqueue(new Callback<rUser>() {
                        @Override
                        public void onResponse(Call<rUser> call, Response<rUser> response) {
                            Toast.makeText(ProfilActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            UsersDataBaseHelper usersDataBaseHelper = new UsersDataBaseHelper(ProfilActivity.this);
                            usersDataBaseHelper.deleteUser(user.get(0).getId().toString());
                            Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<rUser> call, Throwable t) {
                            Toast.makeText(ProfilActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ProfilActivity.this, "Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

    }

    public void showSimpanDialog() {
        View dialogView;
        LayoutInflater inflater;
        AlertDialog.Builder dialog;
        EditText passwordSekarang;
        dialog = new AlertDialog.Builder(ProfilActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_validasi, null);
        passwordSekarang = dialogView.findViewById(R.id.et_password);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Validai");
        dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (passwordSekarang.getText().toString().equals(user.get(0).getPassword())) {
                    APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
                    Call<rUser> updateUser = apiRequestData.updateUser(alamat.getText().toString(), nohp.getText().toString(), passwordSekarang.getText().toString(), Integer.valueOf(id.getText().toString()));
                    updateUser.enqueue(new Callback<rUser>() {
                        @Override
                        public void onResponse(Call<rUser> call, Response<rUser> response) {
                            UsersDataBaseHelper usersDataBaseHelper = new UsersDataBaseHelper(ProfilActivity.this);
                            usersDataBaseHelper.deleteUser(user.get(0).getId().toString());
                            Toast.makeText(ProfilActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ProfilActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<rUser> call, Throwable t) {
                            Toast.makeText(ProfilActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(ProfilActivity.this, "Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

    }
}