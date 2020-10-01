package com.example.moviereviewandratingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.moviereviewandratingapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener { //implementing bottom navigation bar to use its functionalities

    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    public static final String FRAG_TAG_POPULAR = "frag-popular"; //we are using tags so it will help in searching fragments
    public static final String FRAG_TAG_TOP_RATED = "frag-top-rated";
    public static final String FRAG_TAG_SETTINGS = "frag-settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main); // Data Binding
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this); //set bind the current activity with the item clicked

        fragmentManager = getSupportFragmentManager();  // Fragment Manager initialized
        loadPopularMoviesFragment(); // Default fragment which will be present on opening of screen
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { // We check that which button is clicked on th navigation bar and we will open that fragment
        switch (item.getItemId()) {
            case R.id.popular_menu:
                loadPopularMoviesFragment();
                return true;

            case R.id.rated_menu:
                loadTopRatedMoviesFragment();
                return true;
            case R.id.settings_menu:
                loadSettingsFragment();
                return true;
        }
        return false;
    }

    private void loadPopularMoviesFragment() { // Fragment making
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_POPULAR); // we will find fragment by tag with the given tag
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_POPULAR).commit(); // if fragment is not present we will add the fragment in the given tag
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_TOP_RATED, FRAG_TAG_SETTINGS); // and hide the other two tags which are not on screen now
    }

    private void loadTopRatedMoviesFragment() { // Same opening Top rated fragment
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_TOP_RATED);
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
            fragmentManager.beginTransaction().add(R.id.fragment_container, new MovieFragment(), FRAG_TAG_TOP_RATED).commit();
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_POPULAR, FRAG_TAG_SETTINGS);
    }

    private void loadSettingsFragment() { // for settings just print the toast
        fragment = fragmentManager.findFragmentByTag(FRAG_TAG_SETTINGS);
        if (fragment != null) {
            // If fragment already exists, show the Fragment
            fragmentManager.beginTransaction().show(fragment).commit();
        } else {
            // If it doesn't exists, create and add the fragment
//            fragmentManager.beginTransaction().add(R.id.fragment_container, new SettingsFragment(), FRAG_TAG_SETTINGS).commit();
          Toast.makeText(MainActivity.this, "Settings Fragement CLicked", Toast.LENGTH_LONG).show();
        }

        // Hide rest of the fragments if they exist
        hideFragments(FRAG_TAG_POPULAR, FRAG_TAG_TOP_RATED);
    }


    private void hideFragments(String... tags) { // hiding the other fragments
        for (String tag : tags) {
            fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                fragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }
}