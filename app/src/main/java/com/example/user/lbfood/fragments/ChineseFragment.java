package com.example.user.lbfood.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lbfood.adapters.CaptionedImagesAdapter;
import com.example.user.lbfood.models.Food;
import com.example.user.lbfood.R;

import java.util.ArrayList;

public class ChineseFragment extends Fragment implements CaptionedImagesAdapter.Listener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView chineseRecycler = (RecyclerView) inflater.inflate(
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
    public void onClick(int position) {

    }
}
