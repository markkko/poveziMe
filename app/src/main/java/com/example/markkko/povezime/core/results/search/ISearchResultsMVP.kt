package com.example.markkko.povezime.core.results.search

import com.example.markkko.povezime.core.base.BaseUserInteractor
import com.example.markkko.povezime.core.base.BaseUserPresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.SearchRequestReq
import com.example.markkko.povezime.core.models.SearchRequestRes
import com.example.markkko.povezime.core.models.Search
import io.reactivex.Single


interface ISearchResultsMVP {

    interface View : BaseView {

        fun onRequestPosted(request: SearchRequestRes)

    }

    interface Presenter : BaseUserPresenter {

        var view: View

        fun getResults(): List<Offer>

        fun postRequest(offerId: Long)
    }

    interface Interactor : BaseUserInteractor {

        fun getResults(): List<Offer>

        fun postRequest(data: SearchRequestReq) : Single<SearchRequestRes>

        fun getCurrentSearch(): Search
    }

}