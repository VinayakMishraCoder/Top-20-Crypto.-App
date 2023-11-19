package com.example.gweiland_top20crypto.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gweiland_top20crypto.datamodels.ListingItem
import com.example.gweiland_top20crypto.respositories.ListingsRepository
import com.example.gweiland_top20crypto.utility.Constants.DESCENDING
import com.example.gweiland_top20crypto.utility.Constants.SORT_CRITERIA_DEFAULT_MARKET_CAP
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var loading = MutableLiveData<Boolean>(false)

    private var repository = ListingsRepository()

    /**
     * Depict the current criteria chosen.
     * Default Sorting is by market_cap in descending order as per documentation.
     * */
    var currSortingCriteria : String = SORT_CRITERIA_DEFAULT_MARKET_CAP
    var currSortingDirection: String = DESCENDING

    var newCryptoData = MutableLiveData<ArrayList<ListingItem>?>(ArrayList())

    fun getCryptoData(onSuccess: (() -> Unit)? = null, onFailure: ((String?) -> Unit)? = null) {
        loading.value = true
        viewModelScope.launch {
            try {
                /**
                 * API service for getting all the latest listings.
                 * Error Handling - [According to Documentation] if status code received is anything else other than 0 or null, then error.
                 * Above error handling is for both the API calls.
                 * */
                var listingsResponse = repository.getListings(currSortingCriteria, currSortingDirection)
                if(listingsResponse.status?.errorCode == null || listingsResponse.status?.errorCode == 0) {

                    var idsForInfo = ""
                    listingsResponse?.responseData?.let { listings ->
                        for(i in 0 until listings.size) {
                            idsForInfo += listings.get(i).id.toString()
                            if(i != listings.size-1) idsForInfo += ',';
                        }
                    }

                    /**
                     * Another API call for getting logos and other crypto info.
                     * */
                    val cryptoInfoResponse = repository.getCryptoInfo(idsForInfo.trim())

                    if(cryptoInfoResponse.status?.errorCode == null || cryptoInfoResponse.status?.errorCode == 0) {
                        /**
                         * Add respective crypto information related to the crypto item.
                         * */
                        cryptoInfoResponse.responseData?.let {
                            if(listingsResponse?.responseData != null) {
                                for(i in 0 until listingsResponse.responseData!!.size) {
                                    listingsResponse.responseData!![i].cryptoInfoItem = it.get(listingsResponse.responseData!![i].id.toString())
                                }
                            }
                        }
                    } else {
                        /**
                         * Toast error message and load without icons.
                         * */
                        onFailure?.invoke("Error loading logos.")
                    }
                    newCryptoData.value = listingsResponse.responseData
                } else {
                    loading.value = false
                    onFailure?.invoke(listingsResponse.status?.errorMessage)
                    return@launch
                }
                loading.value = false
                onSuccess?.invoke()
            } catch (e: Exception) {
                loading.value = false
                onFailure?.invoke(e.message)
            }
        }
    }
}