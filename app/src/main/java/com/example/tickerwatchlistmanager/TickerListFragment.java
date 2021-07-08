package com.example.tickerwatchlistmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TickerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TickerListFragment extends Fragment {

    ListView tickerListView;
    private TickerViewModel tickerViewModel;

    private AdapterView.OnItemClickListener tickerListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String clickTicker = (String) parent.getItemAtPosition(position);
            tickerViewModel.selectTicker(clickTicker);
            Intent detailIntent = new Intent(getActivity(), TickerDetail.class);
            detailIntent.putExtra("SELECTED_TICKER", clickTicker);
            startActivity(detailIntent);

        }
    };

    public TickerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TickerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TickerListFragment newInstance(String param1, String param2) {
        TickerListFragment fragment = new TickerListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_ticker_list,
                container, false);
        tickerListView = inflate.findViewById(R.id.ticker_list_view);
        tickerListView.setOnItemClickListener(tickerListClickListener);
        return inflate;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tickerViewModel = new ViewModelProvider(getActivity()).get(TickerViewModel.class);

        tickerViewModel.getTickers().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1,
                        tickerViewModel.getTickers().getValue());

                tickerListView.setAdapter(adapter);
            }
        });
    }
}