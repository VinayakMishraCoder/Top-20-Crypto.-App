package com.example.gweiland_top20crypto.utility

object Constants {
    const val BASE_URL = "https://pro-api.coinmarketcap.com"
    const val API_SERVICE_AUTHENTICATION_NAME = "X-CMC_PRO_API_KEY"
    const val API_SERVICE_AUTHENTICATION_KEY = "13a30bdd-5955-42c3-86e8-a74fe6bd67d6"
    const val API_SERVICE_LISTINGS_LIMIT = 20

    /**
     * Default Sorting is by market_cap in descending order as per documentation.
     * Options - MarketCap, Price and Volume-24H
     * */

    const val SORT_CRITERIA_DEFAULT_MARKET_CAP = "market_cap"
    const val SORT_CRITERIA_PRICE = "price"
    const val SORT_CRITERIA_24H = "volume_24h"

    const val ASCENDING = "asc"
    const val DESCENDING = "desc"
}