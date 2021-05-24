package com.example.plutoacademy.view.callback;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.plutoacademy.service.model.Article;
import com.example.plutoacademy.view.ui.home.HomeDetailActivity;

public class OnClickHomeCallback {
	public void onClick(View view, Article article) {
		Context context = view.getContext();
		Intent i = new Intent(context, HomeDetailActivity.class);
		i.putExtra("url", article.getUrl());
		context.startActivity(i);
	}
}
