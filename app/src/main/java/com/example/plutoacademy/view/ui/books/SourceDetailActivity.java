package com.example.plutoacademy.view.ui.books;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ActivitySourceDetailBinding;

import java.util.Objects;


public class SourceDetailActivity extends AppCompatActivity {

    private ActivitySourceDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_source_detail);
        binding.setUrl(getIntent().getStringExtra("url"));
    }
}