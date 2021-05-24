package com.example.plutoacademy.view.ui.experts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ActivityExpertsDetailBinding;

public class ExpertsDetailActivity extends AppCompatActivity {

    private ActivityExpertsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_experts_detail);
        binding.setUrl(getIntent().getStringExtra("url"));
    }

}

