package com.example.tickerwatchlistmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.androidnetworking.common.ANRequest;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTicker extends AppCompatActivity {
    Button saveTickerBtn;
    Button cancelBtn;

    EditText tickerEditText;

    Cursor mCursor;

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ticker = tickerEditText.getText().toString();
            Log.d("Ticker", ticker);

            if(validateTicker(ticker)){

            }else{
                Toast.makeText(getApplicationContext(),"Invalid Ticker",
                        Toast.LENGTH_LONG).show();
            }
        }
    };

    View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent homeIntent = new Intent(AddTicker.this, MainActivity.class);
            startActivity(homeIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticker);

        saveTickerBtn = findViewById(R.id.save_ticker_button);
        cancelBtn = findViewById(R.id.cancel_ticker_button);
        tickerEditText = findViewById(R.id.add_ticker_editText);

        saveTickerBtn.setOnClickListener(saveListener);
        cancelBtn.setOnClickListener(cancelListener);

    }

    private boolean validateTicker(String ticker){

        if(checkSymNum(ticker.toUpperCase()) == "-1"){
            return false;
        }

        //validate ticker using api
        checkForTickerOnline(ticker);
        return true;
    }

    private String checkSymNum(String ticker){
        Pattern pattern = Pattern.compile("\\d|[$&+,:;=\\\\\\\\?@#|/'<>.^*()%!-]");
        Matcher matcher = pattern.matcher(ticker);

        if (matcher.find()) {
            return "-1";

        }
        return ticker;
    }


    public boolean addTicker(String ticker) {

        if (!tickerExists(ticker)) {
            ContentValues mNewValues = new ContentValues();

            mNewValues.put(TickerContentProvider.
                    TABLE_TICKER_COLUMNS[0],ticker);

            getContentResolver().insert(TickerContentProvider.CONTENT_URI,mNewValues);
            return true;
        }else{
            Toast.makeText(getApplicationContext(),"Ticker Exists",
                    Toast.LENGTH_LONG).show();
            return false;
        }


    }

    private boolean tickerExists(String ticker) {
        boolean exist = false;

        mCursor = getContentResolver().query(
                TickerContentProvider.CONTENT_URI,null,
                TickerContentProvider.TABLE_TICKER_COLUMNS[0] +" = ?",
                new String[] {ticker}, null);

        Log.d("cursor", mCursor.toString());

        if(mCursor !=null){
            if(mCursor.getCount() > 0){
                exist = true;
            }

        }

        return exist;
    }

    private boolean checkForTickerOnline(String ticker){

        ANRequest req = AndroidNetworking.get("https://financialmodelingprep.com/api/v3/profile/{ticker}")
                .addPathParameter("ticker",ticker)
                .addQueryParameter("apikey","b16382aaff34f87192ff44598b0e5177")
                .setPriority(Priority.LOW)
                .build();
        req.getAsObjectList(Ticker.class, new ParsedRequestListener<List<Ticker>>() {
            @Override
            public void onResponse(List<Ticker> objects) {
                if(objects.size() != 0){
                    Log.d("HERE", "h");
                    if(addTicker(ticker)){
                        //store ticker
                        Intent detailIntent = new Intent(AddTicker.this, TickerDetail.class);
                        detailIntent.putExtra("TICKER", ticker);
                        startActivity(detailIntent);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error: Ticker Not Listed",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(ANError anError) {
            }
        });

        return false;
    }

}