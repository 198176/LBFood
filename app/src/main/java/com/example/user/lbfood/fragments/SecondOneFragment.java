package com.example.user.lbfood.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lbfood.R;

import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondOneFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_one, container, false);
        view.findViewById(R.id.fab).setOnClickListener(Navigation.createNavigateOnClickListener(R.id.secondTwoFragment));
        return view;
    }

}
