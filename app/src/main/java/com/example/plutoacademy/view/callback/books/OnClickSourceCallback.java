package com.example.plutoacademy.view.callback.books;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.plutoacademy.service.model.books.Books;
import com.example.plutoacademy.service.model.books.Recommenders;
import com.example.plutoacademy.view.ui.books.BooksDetailActivity;

public class OnClickSourceCallback {

    public void onClick(View view, Recommenders recommenders) {
        Context context = view.getContext();
        Intent i = new Intent(context, BooksDetailActivity.class);
        i.putExtra("url", recommenders.getSource());
        context.startActivity(i);
    }

}
