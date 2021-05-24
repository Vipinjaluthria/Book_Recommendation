package com.example.plutoacademy.service.repository;


import com.example.plutoacademy.service.model.Blog;
import com.example.plutoacademy.service.utility.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface BlogService {

    String URL = Constants.API_URL;

    @GET("top-headlines")
    Call<Blog> getBlog(@Query("sources") String source);
}
