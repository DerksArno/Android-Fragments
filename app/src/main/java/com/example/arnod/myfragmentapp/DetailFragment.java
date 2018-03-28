package com.example.arnod.myfragmentapp;


import android.app.Fragment;
//import android.support.v4.app.Fragment;// LET OP DEZE NIET GEBRUIKEN
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by arnod on 22-Feb-18.
 */

public class DetailFragment extends Fragment {

    //public static final String EXTRA_POKEMON = "com.example.arnod.myfirstapp.EXTRA_POKEMON";
    private LinearLayout _view;
    private Pokemon _pokemon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        _view = (LinearLayout)view;
        return view;
    }

    public void SetPokemon(Pokemon pokemon) {
        _pokemon = pokemon;
        _view.setBackgroundColor(pokemon.color);
        ImageView imageview = getView().findViewById(R.id.detailImage);
        imageview.setImageResource(pokemon.picture);
        TextView textview = getView().findViewById(R.id.detailTitle);
        textview.setText(pokemon.name);

        TextView textview2 = getView().findViewById(R.id.rating);
        textview2.setText("Rating " + pokemon.rating);
    }

    //
    public void SetPokemonRating(double rating) {
        _pokemon.SetRating(rating);
        SetPokemon(_pokemon);
    }
}
