package com.example.user.lbfood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lbfood.adapters.CaptionedImagesAdapter;
import com.example.user.lbfood.models.Food;
import com.example.user.lbfood.R;

import java.util.ArrayList;

public class TopFragment extends Fragment implements CaptionedImagesAdapter.Listener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView foodRecycler = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(Food.foods[0]);
        foods.add(Food.foods[4]);
        foods.add(Food.foods[7]);
        foodRecycler.setAdapter(new CaptionedImagesAdapter(foods, this));
        foodRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return foodRecycler;
    }

    @Override
    public void onClick(int position) {

    }
}
