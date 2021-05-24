package com.example.plutoacademy.view.callback.books;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.example.plutoacademy.service.model.books.Books;
import com.example.plutoacademy.view.ui.books.BooksDetailActivity;
import com.example.plutoacademy.view.ui.books.BooksRecommenderActivity;

import java.io.Serializable;

public class OnClickBooksCallback {
    public void onClick(View view, Books books) {
        Context context = view.getContext();
        Intent i = new Intent(context, BooksDetailActivity.class);
        i.putExtra("url", books.getBuy());
        context.startActivity(i);
    }

    public void showRecommenders(View view, Books books) {
        Context context = view.getContext();
        Intent i = new Intent(context, BooksRecommenderActivity.class);
        i.putExtra("recommenders", (Serializable) books.getRecommenders());
        Bundle bundle = new Bundle();
        context.startActivity(i);
    }
}
