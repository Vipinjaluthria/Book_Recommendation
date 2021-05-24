package com.example.plutoacademy.view.ui.books;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.plutoacademy.R;
import com.example.plutoacademy.databinding.ActivityBooksRecommenderBinding;
import com.example.plutoacademy.service.model.books.Recommenders;
import com.example.plutoacademy.view.adapter.books.RecommenderAdapter;

import java.util.List;
import java.util.Objects;

public class BooksRecommenderActivity extends AppCompatActivity {

    ActivityBooksRecommenderBinding binding;
    RecommenderAdapter recommenderAdapter;

    List<? extends Recommenders> recommendersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        recommendersList = (List<? extends Recommenders>) getIntent().getSerializableExtra("recommenders");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_books_recommender);
        recommenderAdapter = new RecommenderAdapter();
        binding.recommenderList.setAdapter(recommenderAdapter);
        binding.setIsLoading(true);
        binding.setIsLoading(false);
        recommenderAdapter.setRecommendersList(recommendersList);
    }
}