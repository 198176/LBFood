package com.example.user.lbfood.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lbfood.models.Food;
import com.example.user.lbfood.R;

public class FoodDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_details, container, false);
        ImageView imageView = view.findViewById(R.id.details_image);
        TextView textView = view.findViewById(R.id.details_text);
        int extra = 0;
        textView.setText(Food.foods[extra].getName());
        imageView.setImageDrawable(getResources().getDrawable(Food.foods[extra].getImageResourceId()));
        return view;
    }

}
