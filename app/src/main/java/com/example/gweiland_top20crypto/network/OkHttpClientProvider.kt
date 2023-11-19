package com.example.gweiland_top20crypto.network

import com.example.gweiland_top20crypto.utility.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

object OkHttpClientProvider {
    fun provide(): OkHttpClient {
        var client = OkHttpClient().newBuilder();
        client.networkInterceptors().add(Interceptor { chain ->
            val req : Request.Builder = chain.request().newBuilder();
            req.addHeader(Constants.API_SERVICE_AUTHENTICATION_NAME,Constants.API_SERVICE_AUTHENTICATION_KEY)
            chain.proceed(req.build())
        })
        return client.build()
    }
}