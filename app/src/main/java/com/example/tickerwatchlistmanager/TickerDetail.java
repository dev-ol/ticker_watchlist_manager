package com.example.tickerwatchlistmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TickerDetail extends AppCompatActivity {

    Button viewListBtn;

    View.OnClickListener viewListListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent homeIntent = new Intent(TickerDetail.this, MainActivity.class);
           startActivity(homeIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker_detail);

        viewListBtn = findViewById(R.id.back_detail_btn);
        viewListBtn.setOnClickListener(viewListListener);
    }
}