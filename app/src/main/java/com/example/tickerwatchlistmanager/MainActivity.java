package com.example.tickerwatchlistmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private static TickerViewModel tickerViewModel ;
    Button addTickerButton;

    View.OnClickListener addTickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent addTickerIntent = new Intent(MainActivity.this, AddTicker.class);
            startActivity(addTickerIntent);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTickerButton= findViewById(R.id.add_ticker_button);
        addTickerButton.setOnClickListener(addTickerListener);

        tickerViewModel = new ViewModelProvider(this).get(TickerViewModel.class);

        Intent intent = getIntent();

        String ticker = intent.getStringExtra("TICKER");
        //ArrayList<String> tickers = intent.getStringArrayListExtra("TICKER_LIST");

      // if(tickers != null){
        //  tickerViewModel.addMultipleTickers(tickers);
      // }


       // if(ticker != null){
           // tickerViewModel.addTicker(ticker);
         //   tickerViewModel.selectTicker(ticker);
        //}

        getAllTickers();

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS)
        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS},
                    101);
        }
    }

    private void getAllTickers(){
        ArrayList<String> tickers = new ArrayList<>();
        Cursor cursor = getContentResolver().query(
                TickerContentProvider.CONTENT_URI,
                new String[]{TickerContentProvider.TABLE_TICKER_COLUMNS[0]},
                null,
                null, null);

        if(cursor != null){
            cursor.moveToFirst();
            for(int i= 0; i < cursor.getCount(); i++){

                tickers.add(cursor.getString(cursor.getColumnIndexOrThrow(
                        TickerContentProvider.TABLE_TICKER_COLUMNS[0])));

                cursor.moveToNext();
            }
            tickerViewModel.addMultipleTickers(tickers);
        }

    }



    public static TickerViewModel getSharedModule(){
       return tickerViewModel;
    }

}