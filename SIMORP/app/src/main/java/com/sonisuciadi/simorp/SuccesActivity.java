package com.sonisuciadi.simorp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SuccesActivity extends AppCompatActivity {

    private static final String ID_PESANAN = "id_pesanan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes);
        TextView idPesanan = findViewById(R.id.id_pesanan);
        idPesanan.setText(getIntent().getExtras().getString(ID_PESANAN));
        Button btnBack = findViewById(R.id.btn_back_to_home);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccesActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                finish();

            }
        });
    }
}