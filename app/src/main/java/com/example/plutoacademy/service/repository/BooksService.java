package com.example.plutoacademy.service.repository;


import com.example.plutoacademy.service.model.books.*;
import com.example.plutoacademy.service.utility.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface BooksService {

    String URL = Constants.BASE_URL;

    @GET("books/list")
    Call<BooksList> getBooksList(@Query("page") int page, @Query("num") int num);

    @GET("books/search")
    Call<BooksSearch> getBooksSearch(@Query("val") String val);
}
