package com.example.user.lbfood.fragments;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lbfood.R;
import com.example.user.lbfood.models.Food;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;

public class FoodDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_details, container, false);
        ImageView imageView = view.findViewById(R.id.details_image);
        TextView textView = view.findViewById(R.id.details_text);

//        int extra = FoodDetailsFragmentArgs.fromBundle(getArguments()).getIdFood();
        int extra = getArguments().getInt("idFood");

        textView.setText(Food.foods.get(extra).getName());
        imageView.setImageDrawable(getResources().getDrawable(Food.foods.get(extra).getImageResourceId()));
        view.findViewById(R.id.details_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initNotify();
                Navigation.findNavController(view).navigate(FoodDetailsFragmentDirections.actionFoodDetailsFragmentToNavSecond());
            }
        });
        return view;
    }

    private PendingIntent createDeepLink() {
        return new NavDeepLinkBuilder(getContext())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.orderedFragment)
                .createPendingIntent();
    }

    private void initNotify() {
        Notification notification = new NotificationCompat.Builder(getContext(), "channel")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getContext().getString(R.string.app_name))
                .setContentText("Zamówienie")
                .setAutoCancel(true)
                .setContentIntent(createDeepLink())
                .build();
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel",
                    "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, notification);
    }

}
