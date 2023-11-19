package com.example.gweiland_top20crypto.network

import com.example.gweiland_top20crypto.datamodels.CryptoInformationResponse
import com.example.gweiland_top20crypto.datamodels.ListingsResponse
import com.example.gweiland_top20crypto.utility.Constants.API_SERVICE_LISTINGS_LIMIT
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getAllListings(
        @Query("limit") limit: Int = API_SERVICE_LISTINGS_LIMIT,
        @Query("sort") sort: String,
        @Query("sort_dir") sort_dir: String
    ): ListingsResponse

    @GET("/v2/cryptocurrency/info")
    suspend fun getCryptoInfo(
        @Query("id") ids: String?
    ): CryptoInformationResponse
}