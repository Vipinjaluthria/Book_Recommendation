package com.example.plutoacademy;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main2);

        // controls the view
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        // builds navGraph
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_books, R.id.navigation_experts, R.id.navigation_about)
                .build();
        // controls the fragment-to-fragment navigation in activity
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // plug in 3 fragment with host fragment in activity
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        // set the UI
        NavigationUI.setupWithNavController(navView, navController);
    }

}