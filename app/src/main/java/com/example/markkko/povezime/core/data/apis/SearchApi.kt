package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.SearchRequestData
import com.example.markkko.povezime.core.models.SearchResultData

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET


interface SearchApi {

    @GET("ride/search")
    fun getSearchResults(@Body data: SearchRequestData): Single<List<SearchResultData>>

}
