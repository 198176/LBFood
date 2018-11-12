package com.example.user.lbfood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lbfood.R;
import com.example.user.lbfood.adapters.CaptionedImagesAdapter;
import com.example.user.lbfood.models.Food;

import java.util.ArrayList;

import androidx.navigation.Navigation;

public class TopFragment extends Fragment implements CaptionedImagesAdapter.Listener {

    RecyclerView foodRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        foodRecycler = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(Food.foods.get(0));
        foods.add(Food.foods.get(4));
        foods.add(Food.foods.get(7));
        foodRecycler.setAdapter(new CaptionedImagesAdapter(foods, this));
        foodRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return foodRecycler;
    }

    @Override
    public void onClick(Food food) {
        Navigation.findNavController(foodRecycler).navigate(TopFragmentDirections.actionTopFragmentToFoodDetailsFragment(Food.foods.indexOf(food)));
    }
}
