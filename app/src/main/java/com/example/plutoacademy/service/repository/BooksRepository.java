package com.example.plutoacademy.service.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.plutoacademy.service.model.books.*;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksRepository {
    private static BooksRepository projectRepository;
    private final BooksService newsService;

    private BooksRepository() {
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
                .baseUrl(BooksService.URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsService = retrofit.create(BooksService.class);
    }

    public synchronized static BooksRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new BooksRepository();
        }
        return projectRepository;
    }

    public LiveData<BooksList> getBooksList(int page, int val) {
        final MutableLiveData<BooksList> data = new MutableLiveData<>();
        newsService.getBooksList(page, val).enqueue(new Callback<BooksList>() {
            @Override
            public void onResponse(Call<BooksList> call, Response<BooksList> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BooksList> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<BooksList> appendBooksList(final BooksList booksList, int page, int val) {
        final MutableLiveData<BooksList> data = new MutableLiveData<>();
        data.setValue(booksList);
        newsService.getBooksList(page, val).enqueue(new Callback<BooksList>() {
            @Override
            public void onResponse(@NotNull Call<BooksList> call, @NotNull Response<BooksList> response) {
                for (Books books: Objects.requireNonNull(Objects.requireNonNull(response.body()).getBooks())) {
                    Objects.requireNonNull(booksList.getBooks()).add(books);
                }
                data.setValue(booksList);
            }

            @Override
            public void onFailure(Call<BooksList> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<BooksSearch> getBooksSearch(final BooksSearch booksSearch, String val) {
        final MutableLiveData<BooksSearch> data = new MutableLiveData<>();
        data.setValue(booksSearch);
        newsService.getBooksSearch(val).enqueue(new Callback<BooksSearch>() {
            @Override
            public void onResponse(Call<BooksSearch> call, Response<BooksSearch> response) {
                for (Books books: Objects.requireNonNull(Objects.requireNonNull(response.body()).getBooks())) {
                    Objects.requireNonNull(booksSearch.getBooks()).add(books);
                }
                data.setValue(booksSearch);
//                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BooksSearch> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}

