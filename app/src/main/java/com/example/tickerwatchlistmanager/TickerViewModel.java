package com.example.tickerwatchlistmanager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class TickerViewModel extends ViewModel {

    private ArrayList<String> tickerList;
    private MutableLiveData<ArrayList<String>> tickers;
    private String selectedTicker;
    private MutableLiveData<String> tickerSelection;

    public LiveData<ArrayList<String>> getTickers(){
        if(tickers == null){
            tickers = new MutableLiveData<>();
            setDefaultTickers();
        }

        return tickers;
    }


    public void addMultipleTickers(ArrayList<String> t){

        if(tickers == null){
            tickers = new MutableLiveData<>();
        }

        tickers.setValue(t);
    }

    public void addTicker(String ticker){

        tickerList = getTickers().getValue();
        if(tickerList.size() == 5){
            tickerList.set(4, ticker);
        }else{
            if(!tickerExists(ticker, tickerList)){
                tickerList.add(ticker);
            }

        }

        tickers.setValue(tickerList);
    }
    private boolean tickerExists(String ticker, ArrayList<String>tickerList){
        boolean exist = false;

        for(int i=0; i<tickerList.size(); i++){
            if(tickerList.get(i).equals(ticker)){
                exist = true;
                break;
            }
        }
        return  exist;
    }
    private void setDefaultTickers(){
        tickerList = new ArrayList<String>();

        tickerList.add("PTON");
        tickerList.add("DIS");
        tickerList.add("JPM");
        tickers.setValue(tickerList);
    }

    public LiveData<String> getSelectedTicker(){

        if(tickerSelection == null){
            tickerSelection = new MutableLiveData<>();
        }
        return tickerSelection;
    }

    public void selectTicker(String ticker){

        if(tickerSelection == null){
            tickerSelection = new MutableLiveData<>();
        }
        tickerSelection.setValue(ticker);
    }

}
