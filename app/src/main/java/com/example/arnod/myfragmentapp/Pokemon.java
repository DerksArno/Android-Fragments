package com.example.arnod.myfragmentapp;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by arnod on 22-Feb-18.
 */

public class Pokemon implements Serializable {
    public int picture;
    public String name;
    public int color;
    public double rating;
    public Pokemon(int picture, String name, int color) {
        this.picture = picture;
        this.name = name;
        this.color = color;
        rating = 0;
    }

    public void SetRating(double rating) {
        this.rating = rating;
    }

    // WORDT GEBRUIKT OM TE ZOEKEN
    @Override
    public String toString() {
        return this.name;
    }
}
