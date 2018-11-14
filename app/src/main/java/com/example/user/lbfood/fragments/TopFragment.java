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
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TopFragment extends Fragment implements CaptionedImagesAdapter.Listener {

    private RecyclerView foodRecycler;
    private NavOptions navOptions;

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

        navOptions = new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setExitAnim(R.anim.slide_out_right)
                .build();

        return foodRecycler;
    }

    @Override
    public void onClick(Food food) {
        Bundle bundle = new Bundle();
        bundle.putInt("idFood", Food.foods.indexOf(food));
        Navigation.findNavController(foodRecycler).navigate(R.id.foodDetailsFragment, bundle);
//        Navigation.findNavController(foodRecycler).navigate(R.id.action_topFragment_to_foodDetailsFragment, bundle);
//        Navigation.findNavController(foodRecycler).navigate(TopFragmentDirections.actionTopFragmentToFoodDetailsFragment(Food.foods.indexOf(food)));
    }
}
