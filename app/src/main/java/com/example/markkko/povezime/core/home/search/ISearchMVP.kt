package com.example.markkko.povezime.core.home.search

import com.example.markkko.povezime.core.base.*
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.models.SearchResultRes
import com.example.markkko.povezime.core.models.SearchResultsReq
import io.reactivex.Single


interface ISearchMVP {

    interface View : BaseView {

        fun showResults(results: List<Offer>)

    }

    interface Presenter : BaseUserPresenter {

        fun getSearchResults(data: SearchResultsReq)

        var view: View

    }

    interface Interactor : BaseUserInteractor {

        fun getSearchResults(data: SearchResultsReq): Single<List<Offer>>

    }
}