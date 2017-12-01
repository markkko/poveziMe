package com.example.markkko.povezime.core.home.search


import android.content.Context
import com.example.markkko.povezime.app.user.UserRepository
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.example.markkko.povezime.core.models.SearchRequest
import com.example.markkko.povezime.core.models.SearchResult
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchApi: SearchApi,
                                           private val userRepository: UserRepository) : ISearchMVP.Interactor {


    override fun getSearchResults(data: SearchRequest): Single<List<SearchResult>> =
            searchApi.getSearchResults(data)

    override fun me(): User = userRepository.user
}
