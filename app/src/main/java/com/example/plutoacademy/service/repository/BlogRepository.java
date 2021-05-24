package com.example.plutoacademy.service.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plutoacademy.service.model.Blog;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlogRepository {
    private static BlogRepository projectRepository;
    private final BlogService newsService;

    private BlogRepository() {
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
                .baseUrl(BlogService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsService = retrofit.create(BlogService.class);
    }

    public synchronized static BlogRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new BlogRepository();
        }
        return projectRepository;
    }

    public LiveData<Blog> getBlog(String source) {
        final MutableLiveData<Blog> data = new MutableLiveData<>();
        newsService.getBlog(source).enqueue(new Callback<Blog>() {
            @Override
            public void onResponse(Call<Blog> call, Response<Blog> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Blog> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}

