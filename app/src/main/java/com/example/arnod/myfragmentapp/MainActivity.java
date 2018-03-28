package com.example.arnod.myfragmentapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Rating;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity implements OverviewFragment.OnItemSelectedListener {

    private OverviewFragment _overviewFragment;
    private DetailFragment _detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main);
            _overviewFragment = (OverviewFragment) getFragmentManager().findFragmentById(R.id.overviewFragment);
        }
        else {
            setContentView(R.layout.activity_main_portrait_rechtop);

            _overviewFragment = new OverviewFragment();
            getFragmentManager().beginTransaction().add(R.id.container, _overviewFragment, "list").commit();
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    // Overschrijf het menu door een ander menu te inflaten.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.toolbar_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // IDK wat dit is
                /*if( !searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();*/

                _overviewFragment.Search(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    _overviewFragment.ResetSearch();
                }
            }
        });

        // SPINNER SETUP
        Spinner spinner = (Spinner)this.findViewById(R.id.spinner);
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("");
        adapter.add("Bulbasaurr");
        adapter.add("Dragonite");
        adapter.add("Pikachu");
        spinner.setAdapter(adapter);

        // Make the items in the adapter clickable
        AdapterView.OnItemSelectedListener mMessageClickedHandler = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = (String)adapter.getItem(position);
                _overviewFragment.Search(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        };
        spinner.setOnItemSelectedListener(mMessageClickedHandler);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_favorite:
                ShowFavoriteDialog();
                return true;

            case R.id.toolbar_search:
                //OverviewFragment fragment = (OverviewFragment) getFragmentManager().findFragmentById(R.id.overviewFragment);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void ShowFavoriteDialog() {
        if (_detailFragment != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.btn_star_big_off);
            builder.setTitle("Rating");
            builder.setMessage("Please enter your rating");
            final View v = View.inflate(this, R.layout.dialog_favorite, null);
            builder.setView(v);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    RatingBar ratingbar = (RatingBar)v.findViewById(R.id.ratingBar);
                    _detailFragment.SetPokemonRating(ratingbar.getRating());
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }
    }

    @Override
    public void onItemSelected(Pokemon item) {

        _detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (_detailFragment != null && _detailFragment.isInLayout())
        {
            _detailFragment.SetPokemon(item);
        }
        else
        {
            _detailFragment = new DetailFragment();

            FragmentManager fm = getFragmentManager();
            fm.beginTransaction().replace(R.id.container, _detailFragment, "detail").addToBackStack("detailTag").commit();

            fm.executePendingTransactions();
            _detailFragment.SetPokemon(item);
        }
    }
}
