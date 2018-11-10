package com.example.user.lbfood.models;

import com.example.user.lbfood.R;

public class Food {

    private String name;
    private int imageResourceId;
    private int type;
    public static final int PIZZA = 1;
    public static final int CHINESE = 2;
    public static final int KEBAB = 3;

    public static final Food[] foods = {
            new Food("Diavolo", R.drawable.diavolo, PIZZA),
            new Food("Funghi", R.drawable.funghi, PIZZA),
            new Food("Diavo", R.drawable.diavolo, PIZZA),
            new Food("Hunghi", R.drawable.funghi, PIZZA),
            new Food("CimCiaCho", R.drawable.ciao, CHINESE),
            new Food("HaLong", R.drawable.kurczak, CHINESE),
            new Food("ChoNoTu", R.drawable.ciao, CHINESE),
            new Food("Rollo", R.drawable.rollo, KEBAB),
            new Food("Kebab box", R.drawable.box, KEBAB),
            new Food("Tortilla", R.drawable.rollo, KEBAB)
    };

    private Food(String name, int imageResourceId, int type) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getType() {
        return type;
    }
}
