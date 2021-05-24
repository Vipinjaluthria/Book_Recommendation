package com.example.plutoacademy.service.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plutoacademy.service.model.experts.Experts;
import com.example.plutoacademy.service.model.experts.ExpertsDetails;
import com.example.plutoacademy.service.model.experts.ExpertsList;
import com.example.plutoacademy.service.model.experts.ExpertsSearch;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExpertsDetialsRepository {
    private static ExpertsDetialsRepository projectRepository;
    private final ExpertsService newsService;

    private ExpertsDetialsRepository() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl
                        .newBuilder()
                        .addQueryParameter("apiKey", "466a36ba9e0647238afd0065be34860a")
                        .build();

                Request request = original
                        .newBuilder()
                        .url(url).build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ExpertsService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsService = retrofit.create(ExpertsService.class);
    }

    public synchronized static ExpertsDetialsRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new ExpertsDetialsRepository();
        }
        return projectRepository;
    }


    public LiveData<ExpertsDetails> getExpertsDetails(String val) {
        final MutableLiveData<ExpertsDetails> data = new MutableLiveData<>();
        Log.d("asdfghjkl", "eeeeeeeeeee");
        Call<ExpertsDetails> call = newsService.getExpertsDetails(val);
        Log.d("asdfghjkl", "llllllllllll");
        call.enqueue(new Callback<ExpertsDetails>() {
            @Override
            public void onResponse(Call<ExpertsDetails> call, Response<ExpertsDetails> response) {
                Log.d("asdfghjkl", response.message());
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ExpertsDetails> call, Throwable t) {
                Log.d("asdfghjkl", "hjhjjjjjk");
                data.setValue(null);
            }
        });
        Log.d("asdfghjkl", "qqqqqqq");
        return data;
    }

}

