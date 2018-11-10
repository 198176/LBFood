package com.example.user.lbfood.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.List;

public class RestaurantFragment extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        List<String> list = Arrays.asList("Pizzeryjka", "Chinolek", "Turek");
        setListAdapter(new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                list));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
