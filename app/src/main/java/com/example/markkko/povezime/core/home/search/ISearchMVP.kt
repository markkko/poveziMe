package com.example.markkko.povezime.core.home.search

import com.example.markkko.povezime.core.base.BaseUserInteractor
import com.example.markkko.povezime.core.base.BaseUserPresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import io.reactivex.Single


interface ISearchMVP {

    interface View : BaseView {

        fun showResults(results: List<Offer>)

    }

    interface Presenter : BaseUserPresenter {

        fun getSearchResults(data: Search)

        var view: View

    }

    interface Interactor : BaseUserInteractor {

        fun getSearchResults(data: Search): Single<List<Offer>>

    }
}