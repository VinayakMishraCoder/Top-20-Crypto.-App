package com.example.gweiland_top20crypto.network

import com.example.gweiland_top20crypto.utility.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val apiInstance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClientProvider.provide())
            .build()
            .create(ApiService::class.java)
    }
}