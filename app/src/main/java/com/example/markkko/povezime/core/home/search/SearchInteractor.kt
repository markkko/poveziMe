package com.example.markkko.povezime.core.home.search


import android.util.Log
import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.example.markkko.povezime.core.models.*
import com.example.markkko.povezime.core.results.search.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchApi: SearchApi,
                                           private val userRepository: UserRepository,
                                           private val searchRepository: SearchRepository) : ISearchMVP.Interactor {


    override fun getSearchResults(data: SearchResultsReq): Single<List<Offer>> =
            searchApi.getSearchResults(data)
                    .doOnSuccess {
                        searchRepository.currentSearch = it.search
                        Log.d("search", it.search.date)
                    }
                    .doOnSuccess { searchRepository.results = it.offers as ArrayList}
                    .map { it.offers }

    override fun me(): User = userRepository.user
}
