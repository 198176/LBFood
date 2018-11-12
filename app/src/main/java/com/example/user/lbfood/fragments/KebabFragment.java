package com.example.user.lbfood.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lbfood.R;
import com.example.user.lbfood.adapters.CaptionedImagesAdapter;
import com.example.user.lbfood.models.Food;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class KebabFragment extends Fragment implements CaptionedImagesAdapter.Listener {

    RecyclerView kebabRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        kebabRecycler = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ArrayList<Food> kebabs = new ArrayList<>();
        for (Food food : Food.foods) {
            if (food.getType() == Food.KEBAB) {
                kebabs.add(food);
            }
        }
        kebabRecycler.setAdapter(new CaptionedImagesAdapter(kebabs, this));
        kebabRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return kebabRecycler;
    }

    @Override
    public void onClick(Food food) {
        Navigation.findNavController(kebabRecycler).navigate(KebabFragmentDirections.actionKebabFragmentToFoodDetailsFragment(Food.foods.indexOf(food)));
    }
}
