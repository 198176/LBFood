package com.example.user.lbfood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.ListFragment;
import androidx.navigation.Navigation;

public class RestaurantFragment extends ListFragment {

    List<String> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        list = Arrays.asList("Pizzeryjka", "Chinolek", "Turek");
        setListAdapter(new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                list));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Navigation.findNavController(v).navigate(RestaurantFragmentDirections.actionRestaurantFragmentToRestaurantDetailsFragment(list.get(position), position));
    }
}
