package com.example.markkko.povezime.core.home.search

import com.example.markkko.povezime.core.base.*
import com.example.markkko.povezime.core.models.SearchRequest
import com.example.markkko.povezime.core.models.SearchResult
import io.reactivex.Single


interface ISearchMVP {

    interface View : BaseView {

        fun showResults(results: List<SearchResult>)

    }

    interface Presenter : BaseUserPresenter {

        fun getSearchResults(data: SearchRequest)

        var view: View

    }

    interface Interactor : BaseUserInteractor {

        fun getSearchResults(data: SearchRequest): Single<List<SearchResult>>

    }
}