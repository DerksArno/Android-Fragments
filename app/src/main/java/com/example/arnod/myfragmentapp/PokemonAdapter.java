package com.example.arnod.myfragmentapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by arnod on 22-Feb-18.
 */

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    public PokemonAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return PokemonItem(position, convertView, parent);
    }

    public View PokemonItem(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.pokemon_item, parent, false);
        }
        Pokemon pokemon = (Pokemon) getItem(position);
        ImageView imageview = convertView.findViewById(R.id.imageView);
        imageview.setImageResource(pokemon.picture);
        TextView textview = convertView.findViewById(R.id.textView);
        textview.setText(pokemon.name);
        return convertView;
    }

    public void addPokemons() {
        this.add(new Pokemon(R.drawable.bulbasaur, "Bulbasaurr", Color.argb(255, 91, 201, 99)));
        this.add(new Pokemon(R.drawable.dragonite, "Dragonite", Color.argb(255, 0, 50, 255)));
        this.add(new Pokemon(R.drawable.pikachu, "Pikachu", Color.argb(255, 235, 244, 66)));
    }
}
