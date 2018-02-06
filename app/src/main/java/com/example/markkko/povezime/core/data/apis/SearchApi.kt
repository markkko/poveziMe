package com.example.markkko.povezime.core.data.apis

import com.example.markkko.povezime.core.models.*

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface SearchApi {

    @POST("search/ride")
    fun getSearchResults(@Body data: Search): Single<SearchResults>

    @POST("search/ride/request")
    fun postRequest(@Body data: RequestReq) : Single<MatchInfo>

}
