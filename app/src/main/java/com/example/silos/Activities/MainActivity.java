package com.example.silos.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.silos.Fragments.GrainFragment;
import com.example.silos.Fragments.HomeFragment;
import com.example.silos.Fragments.SiloFragment;
import com.example.silos.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.navigation_day:{
                Fragment homefragment = HomeFragment.newInstance();
                openFragment(homefragment);
                break;
            }

            case R.id.navigation_silo:{
                Fragment recipesFragment = SiloFragment.newInstance();
                openFragment(recipesFragment);
                break;
            }

            case R.id.navigation_aside:{
                Fragment categoryFragment = GrainFragment.newInstance();
                openFragment(categoryFragment);
                break;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(this);

        Fragment homefragment = HomeFragment.newInstance();
        openFragment(homefragment);
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
