package com.example.tickerwatchlistmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            Log.d("SMSReceiver", "received sms");

            try{

                if(bundle != null){

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    String format = bundle.getString("format");

                    Log.d("SMSReceiver", "onReceive: " + pdusObj.toString());

                    for(int i =0; i < pdusObj.length; i++){

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte []) pdusObj[i], format) ;
                        String message = currentMessage.getDisplayMessageBody();

                        Log.d("SMSReceiver", "sms msg: " + message);

                        String ticker = checkValidTicker(message);

                        Intent myIntent = new Intent(context, MainActivity.class);

                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                        if(ticker == null){

                            String toastMsg = "No valid watchlist entry was found";
                            Toast.makeText(context, toastMsg, Toast.LENGTH_LONG)
                                    .show();

                        }else if(ticker == "-1"){
                            String toastMsg = "Ticker was invalid";
                            Toast.makeText(context, toastMsg, Toast.LENGTH_LONG)
                                    .show();
                        } else{

                            myIntent.putExtra("TICKER",ticker);
                        }
                        TickerViewModel tickerViewModel = MainActivity.getSharedModule();

                        myIntent.putStringArrayListExtra("TICKER_LIST",tickerViewModel.getTickers().getValue() );
                        context.startActivity(myIntent);

                    }
                }
            }catch(Exception  e){
                Log.e("SMSReceiver", e.toString());
            }
        }
    }

    private String checkValidTicker(String message){

        Pattern pattern = Pattern.compile("Ticker:<.*.>");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {

            String ticker = matcher.group(0).substring(8, matcher.group(0).length()-1);

            return checkSymNum(ticker.toUpperCase());

        }

        return null;

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
