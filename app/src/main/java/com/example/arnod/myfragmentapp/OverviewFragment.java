package com.example.arnod.myfragmentapp;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by arnod on 22-Feb-18.
 */

public class OverviewFragment extends Fragment {

    private ListView listView2;
    private PokemonAdapter adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.overview_fragment, container, false);

        adapter2 = new PokemonAdapter(getActivity(), R.layout.pokemon_item);
        adapter2.addPokemons();
        listView2 = view.findViewById(R.id.list_view);
        listView2.setAdapter(adapter2);

        // Make the items in the adapter clickable
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Pokemon item = adapter2.getItem(position);
                listener.onItemSelected(item);
            }
        };
        listView2.setOnItemClickListener(mMessageClickedHandler);

        // CONTEXT MENU
        registerForContextMenu(listView2);

        return view;
    }

    // Context menu
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.blue:
                item.setChecked(true);
                int color = Color.rgb(0,0,255);
                listView2.setBackgroundColor(color);
                return true;
            case R.id.red:
                item.setChecked(true);
                int color2 = Color.rgb(255,0,0);
                listView2.setBackgroundColor(color2);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void Search(String pokemonName) {

        adapter2.getFilter().filter(pokemonName);
    }

    public void ResetSearch() {

        adapter2.getFilter().filter("");
    }

    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener
    {
        public void onItemSelected(Pokemon item);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) getActivity();
        }
    }

}
