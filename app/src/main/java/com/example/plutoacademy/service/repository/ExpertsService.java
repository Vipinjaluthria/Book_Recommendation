package com.example.plutoacademy.service.repository;


import com.example.plutoacademy.service.model.experts.ExpertsDetails;
import com.example.plutoacademy.service.model.experts.ExpertsList;
import com.example.plutoacademy.service.model.experts.ExpertsSearch;
import com.example.plutoacademy.service.utility.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ExpertsService {

    String URL = Constants.BASE_URL;

    @GET("experts/list")
    Call<ExpertsList> getExpertsList(@Query("page") int page, @Query("num") int num);

    @GET("experts/search")
    Call<ExpertsSearch> getExpertsSearch(@Query("val") String val);

    @GET("experts/details")
    Call<ExpertsDetails> getExpertsDetails(@Query("val") String val);
}
