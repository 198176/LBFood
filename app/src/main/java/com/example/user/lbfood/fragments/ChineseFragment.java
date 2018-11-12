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

public class ChineseFragment extends Fragment implements CaptionedImagesAdapter.Listener {

    RecyclerView chineseRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        chineseRecycler = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ArrayList<Food> chineses = new ArrayList<>();
        for (Food food : Food.foods) {
            if (food.getType() == Food.CHINESE) {
                chineses.add(food);
            }
        }
        chineseRecycler.setAdapter(new CaptionedImagesAdapter(chineses, this));
        chineseRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return chineseRecycler;
    }

    @Override
    public void onClick(Food food) {
        Navigation.findNavController(chineseRecycler).navigate(ChineseFragmentDirections.actionChineseFragmentToFoodDetailsFragment(Food.foods.indexOf(food)));
    }
}
