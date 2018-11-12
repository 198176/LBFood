package com.example.user.lbfood.adapters;

import android.graphics.drawable.Drawable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.lbfood.R;
import com.example.user.lbfood.models.Food;

import java.util.ArrayList;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private ArrayList<Food> foods;
    private Listener listener;

    public CaptionedImagesAdapter(ArrayList<Food> foods, Listener listener) {
        this.foods = foods;
        this.listener = listener;
    }

    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = cardView.findViewById(R.id.info_image);
        Drawable drawable = cardView.getResources().getDrawable(foods.get(position).getImageResourceId());
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(foods.get(position).getName());
        TextView textView = cardView.findViewById(R.id.info_text);
        textView.setText(foods.get(position).getName());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(foods.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public interface Listener {
        void onClick(Food food);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
