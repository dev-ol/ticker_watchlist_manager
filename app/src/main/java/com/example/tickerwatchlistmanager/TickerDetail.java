package com.example.tickerwatchlistmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TickerDetail extends AppCompatActivity {

    Button viewListBtn;
    TextView tickerTv;
    TextView priceTextView;
    TextView companyNameTv;
    TextView ipoDateTextView;
    TextView stockPriceTv;
    TextView vAverageTv;
    TextView mkCapTv;
    TextView lastDivTv;
    TextView week52RangeTv;
    TextView betaTv;
    TextView ceoNameTextView;
    TextView websiteTv;
    TextView descriptionTv;
    TextView sectorTv;
    TextView industryTv;
    TextView exchangeTextView;
    ImageView companyLogoIv;

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

        Intent intent = getIntent();

        String ticker = intent.getStringExtra("TICKER");
        Log.d("TICKER2", ticker);
        getTickerDetail(ticker);

        viewListBtn = findViewById(R.id.back_detail_btn);
        viewListBtn.setOnClickListener(viewListListener);

        tickerTv =findViewById(R.id.ticker_tv);
        priceTextView =findViewById(R.id.price_textView);
        companyNameTv =findViewById(R.id.company_name_tv);
        ipoDateTextView =findViewById(R.id.ipo_date_textView);
        stockPriceTv =findViewById(R.id.stock_price_tv);
        vAverageTv =findViewById(R.id.v_average_tv);
        mkCapTv =findViewById(R.id.mk_cap_tv);
        lastDivTv =findViewById(R.id.last_div_tv);
        ipoDateTextView =findViewById(R.id.ipo_date_textView);
        week52RangeTv =findViewById(R.id.week_52_range_tv);
        betaTv =findViewById(R.id.beta_tv);
        ceoNameTextView =findViewById(R.id.ceo_name_textView);
        websiteTv =findViewById(R.id.website_tv);
        descriptionTv =findViewById(R.id.description_tv);

        sectorTv =findViewById(R.id.sector_tv);
        industryTv =findViewById(R.id.industry_tv);
        exchangeTextView =findViewById(R.id.exchange_textView);
        companyLogoIv = findViewById(R.id.company_logo_iv);
    }


    private void getTickerDetail(String ticker){
        ANRequest req = AndroidNetworking.get("https://financialmodelingprep.com/api/v3/profile/{ticker}")
                .addPathParameter("ticker",ticker)
                .addQueryParameter("apikey","b16382aaff34f87192ff44598b0e5177")
                .setPriority(Priority.LOW)
                .build();
        req.getAsObjectList(Ticker.class, new ParsedRequestListener<List<Ticker>>() {
            @Override
            public void onResponse(List<Ticker> objects) {

                if(objects.size() != 0){
                    Ticker ticker = objects.get(0);
                    setTextViews(ticker);

                }else{
                    Toast.makeText(getApplicationContext(),"Error: Ticker Not Listed",
                            Toast.LENGTH_LONG).show();
                    Intent homeIntent = new Intent(TickerDetail.this, MainActivity.class);
                    startActivity(homeIntent);
                }
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(getApplicationContext(),"Unexpected Error",
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    private void setTextViews(Ticker ticker){

        Log.d("Detailticker", ticker.getCompanyName());

        tickerTv.setText(ticker.getSymbol());
        priceTextView.setText("$" + ticker.getPrice().toString());
        companyNameTv.setText(ticker.getCompanyName());
        ipoDateTextView.setText(ticker.getIpoDate());
        stockPriceTv.setText("$" + ticker.getChanges().toString());
        vAverageTv.setText(ticker.getVolAvg().toString());
        lastDivTv.setText(ticker.getLastDiv().toString());
        week52RangeTv.setText(ticker.getRange());
        betaTv.setText(ticker.getBeta().toString());
        ceoNameTextView.setText(ticker.getCeo());
        websiteTv.setText(ticker.getWebsite());
        descriptionTv.setText(ticker.getDescription());
        sectorTv.setText(ticker.getSector());
        industryTv.setText(ticker.getIndustry());
        exchangeTextView.setText(ticker.getExchange());
        mkCapTv.setText(ticker.getMktCap().toString());

        Picasso.get().load(ticker.getImage()).into(companyLogoIv);


    }
}