package com.example.tickerwatchlistmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddTicker extends AppCompatActivity {
    Button saveTickerBtn;
    Button cancelBtn;

    EditText tickerEditText;

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ticker = tickerEditText.getText().toString();
            Log.d("Ticker", ticker);

            if(validateTicker(ticker)){
                //store ticker
                Intent homeIntent = new Intent(AddTicker.this, MainActivity.class);
                startActivity(homeIntent);
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

        //check ticker with api


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
}