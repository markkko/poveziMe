package com.example.markkko.povezime.core.home.search


import android.content.Context

import com.example.markkko.povezime.app.di.activity.ActivityScope
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.example.markkko.povezime.core.models.SearchRequestData
import com.example.markkko.povezime.core.models.SearchResultData
import com.example.markkko.povezime.core.util.SchedulerProvider

import javax.inject.Inject

import io.reactivex.Single

@ActivityScope
class SearchInteractorImpl @Inject
constructor(private val searchApi: SearchApi, private val schedulerProvider: SchedulerProvider) : SearchInteractor {

    @Inject
    internal var context: Context? = null

    override fun unbind() {

    }

    override fun getSearchResults(data: SearchRequestData): Single<List<SearchResultData>> {
        return searchApi.getSearchResults(data)
    }

}
