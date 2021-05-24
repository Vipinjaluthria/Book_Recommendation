package com.example.plutoacademy.view.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ActivityHomeDetailBinding;


public class HomeDetailActivity extends AppCompatActivity {

    private ActivityHomeDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_detail);
        binding.setUrl(getIntent().getStringExtra("url"));
    }
}