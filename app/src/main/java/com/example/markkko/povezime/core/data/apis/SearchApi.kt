package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.SearchRequest
import com.example.markkko.povezime.core.models.SearchResult

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface SearchApi {

    @POST("search/ride")
    fun getSearchResults(@Body data: SearchRequest): Single<List<SearchResult>>

}
