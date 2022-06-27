package com.sonisuciadi.simorp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.sonisuciadi.simorp.DataBaseHelper.UsersDataBaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsersDataBaseHelper usersDataBaseHelper = new UsersDataBaseHelper(MainActivity.this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Cursor user = usersDataBaseHelper.getUser();
                if (user.getCount() == 0) {
                    Intent login = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(login);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                } else {
                    Intent login = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(login);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                }


                finish();

            }
        }, 1500);
    }
}