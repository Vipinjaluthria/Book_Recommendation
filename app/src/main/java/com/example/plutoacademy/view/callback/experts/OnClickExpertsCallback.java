package com.example.plutoacademy.view.callback.experts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.plutoacademy.service.model.books.Books;
import com.example.plutoacademy.service.model.experts.Experts;
import com.example.plutoacademy.service.model.experts.Links;
import com.example.plutoacademy.view.ui.books.BooksDetailActivity;
import com.example.plutoacademy.view.ui.books.BooksRecommenderActivity;
import com.example.plutoacademy.view.ui.experts.ExpertsProfileActivity;

import java.io.Serializable;

public class OnClickExpertsCallback {
    public void onClick(View view, Experts experts) {
        Context context = view.getContext();
        Intent i = new Intent(context, ExpertsProfileActivity.class);
        i.putExtra("slug", experts.getSlug());
        context.startActivity(i);
    }

    public void func(View view, String url) {
        Context context = view.getContext();
        Intent i = new Intent(context, BooksDetailActivity.class);
        i.putExtra("url", url);
        context.startActivity(i);
    }

    public void onLinksClick(View view, Links links) {
        String url = links.getLink();
        func(view, url);
    }

    public void onBooksClick(View view, Books books) {
        String url = books.getBuy();
        func(view, url);
    }

    public void showRecommenders(View view, Books books) {
        Context context = view.getContext();
        Intent i = new Intent(context, BooksRecommenderActivity.class);
        i.putExtra("recommenders", (Serializable) books.getRecommenders());
        Bundle bundle = new Bundle();
        context.startActivity(i);
    }


}
