package com.example.user.lbfood.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lbfood.R;
import com.example.user.lbfood.adapters.CaptionedImagesAdapter;
import com.example.user.lbfood.models.Food;

import java.util.ArrayList;

import androidx.navigation.Navigation;

public class PizzaFragment extends Fragment implements CaptionedImagesAdapter.Listener {

    RecyclerView pizzaRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pizzaRecycler = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ArrayList<Food> pizzas = new ArrayList<>();
        for (Food food : Food.foods) {
            if (food.getType() == Food.PIZZA) {
                pizzas.add(food);
            }
        }
        pizzaRecycler.setAdapter(new CaptionedImagesAdapter(pizzas, this));
        pizzaRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return pizzaRecycler;
    }

    @Override
    public void onClick(Food food) {
        Navigation.findNavController(pizzaRecycler).navigate(PizzaFragmentDirections.actionPizzaFragmentToFoodDetailsFragment(Food.foods.indexOf(food)));
    }
}