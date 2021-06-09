package com.example.tickerwatchlistmanager;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoWebFragment extends Fragment {

    TextView testTextView;
    private TickerViewModel tickerViewModel;
    WebView infoWebView;
    String HOME_PAGE ;

    public InfoWebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoWebFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoWebFragment newInstance(String param1, String param2) {
        InfoWebFragment fragment = new InfoWebFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_info_web,
                container, false);

        testTextView = (TextView) inflate.findViewById(R.id.test_text_view);
        testTextView.setText("Loaded");

        infoWebView = inflate.findViewById(R.id.info_web_view);

        infoWebView.setWebViewClient(new WebViewClient());

        HOME_PAGE = getResources().getText(R.string.DEFAULT_URL).toString();
        infoWebView.getSettings().setJavaScriptEnabled(true);
        infoWebView.loadUrl(HOME_PAGE);


        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tickerViewModel = new ViewModelProvider(getActivity()).get(TickerViewModel.class);

        tickerViewModel.getSelectedTicker().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String strings) {
             String ticker = tickerViewModel.getSelectedTicker().getValue();

             ticker = HOME_PAGE + "/symbol/" + ticker;
             Uri uri = Uri.parse(ticker);

             if(uri.getScheme() == null){
                 ticker =  "https://" + ticker;
             }
             infoWebView.loadUrl(ticker);
            }
        });
    }
}