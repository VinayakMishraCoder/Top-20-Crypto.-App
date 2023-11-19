package com.example.gweiland_top20crypto.respositories

import com.example.gweiland_top20crypto.datamodels.CryptoInformationResponse
import com.example.gweiland_top20crypto.datamodels.ListingsResponse
import com.example.gweiland_top20crypto.network.RetrofitInstance

class ListingsRepository {

    suspend fun getListings(sort: String, sortDirection: String) : ListingsResponse {
        return RetrofitInstance.apiInstance.getAllListings(sort = sort, sort_dir = sortDirection)
    }

    suspend fun getCryptoInfo(ids: String? = null) : CryptoInformationResponse {
        return RetrofitInstance.apiInstance.getCryptoInfo(ids)
    }

}