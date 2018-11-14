package com.example.user.lbfood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.lbfood.R;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class RestaurantDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_details, container, false);
        TextView textView = view.findViewById(R.id.tvDetails);

        String extra = RestaurantDetailsFragmentArgs.fromBundle(getArguments()).getRestName();
        final int position = RestaurantDetailsFragmentArgs.fromBundle(getArguments()).getRestID();

        textView.setText(extra);
        view.findViewById(R.id.btMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        Navigation.findNavController(v).navigate(RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToPizzaFragment());
                        break;
                    case 1:
                        Navigation.findNavController(v).navigate(RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToChineseFragment());
                        break;
                    case 2:
                        Navigation.findNavController(v).navigate(RestaurantDetailsFragmentDirections.actionRestaurantDetailsFragmentToKebabFragment());
                        break;
                }
            }
        });
        return view;
    }

}
