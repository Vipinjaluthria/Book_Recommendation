package com.example.plutoacademy.service.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plutoacademy.service.model.experts.*;

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

public class ExpertsRepository {
    private static ExpertsRepository projectRepository;
    private final ExpertsService newsService;

    private ExpertsRepository() {
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

    public synchronized static ExpertsRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new ExpertsRepository();
        }
        return projectRepository;
    }

    public LiveData<ExpertsList> getExpertsList(int page, int val) {
        final MutableLiveData<ExpertsList> data = new MutableLiveData<>();
        newsService.getExpertsList(page, val).enqueue(new Callback<ExpertsList>() {
            @Override
            public void onResponse(Call<ExpertsList> call, Response<ExpertsList> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ExpertsList> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ExpertsList> appendExpertsList(final ExpertsList expertsList, int page, int val) {
        final MutableLiveData<ExpertsList> data = new MutableLiveData<>();
        data.setValue(expertsList);
        newsService.getExpertsList(page, val).enqueue(new Callback<ExpertsList>() {
            @Override
            public void onResponse(@NotNull Call<ExpertsList> call, @NotNull Response<ExpertsList> response) {
                for (Experts experts: Objects.requireNonNull(Objects.requireNonNull(response.body()).getExperts())) {
                    Objects.requireNonNull(expertsList.getExperts()).add(experts);
                }
                data.setValue(expertsList);
            }

            @Override
            public void onFailure(Call<ExpertsList> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ExpertsSearch> getExpertsSearch(final ExpertsSearch expertsSearch, String val) {
        final MutableLiveData<ExpertsSearch> data = new MutableLiveData<>();
        data.setValue(expertsSearch);
        newsService.getExpertsSearch(val).enqueue(new Callback<ExpertsSearch>() {
            @Override
            public void onResponse(Call<ExpertsSearch> call, Response<ExpertsSearch> response) {
                for (Experts experts: Objects.requireNonNull(Objects.requireNonNull(response.body()).getExperts())) {
                    Objects.requireNonNull(expertsSearch.getExperts()).add(experts);
                }
                data.setValue(expertsSearch);
//                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ExpertsSearch> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<ExpertsDetails> getExpertsDetails(String val) {
        final MutableLiveData<ExpertsDetails> data = new MutableLiveData<>();
        Log.d("asdfghjkl", "eeeeeeeeeee");
        newsService.getExpertsDetails(val).enqueue(new Callback<ExpertsDetails>() {
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

