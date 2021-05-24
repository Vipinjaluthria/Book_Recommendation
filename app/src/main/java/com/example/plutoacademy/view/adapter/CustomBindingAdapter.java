package com.example.plutoacademy.view.adapter;


import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.example.plutoacademy.service.utility.DateUtils;


public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("dateText")
    public static void convertToDate(TextView view, String date) {
        view.setText(DateUtils.Companion.convertToDateString(date));
    }

    @BindingAdapter("loadurl")
    public static void loadurl(WebView mWebview, String url) {
        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            }
        });
        mWebview.loadUrl(url);
    }

}