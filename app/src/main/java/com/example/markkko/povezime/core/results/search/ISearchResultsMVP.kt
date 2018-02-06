package com.example.markkko.povezime.core.results.search

import com.example.markkko.povezime.core.base.BaseUserInteractor
import com.example.markkko.povezime.core.base.BaseUserPresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.*
import io.reactivex.Single


interface ISearchResultsMVP {

    interface View : BaseView {

        fun onRequestPosted(request: MatchInfo)

    }

    interface Presenter : BaseUserPresenter {

        var view: View

        fun getResults(): List<Offer>

        fun postRequest(offerId: Long)
    }

    interface Interactor : BaseUserInteractor {

        fun getResults(): List<Offer>

        fun postRequest(data: RequestReq) : Single<MatchInfo>

        fun getCurrentSearch(): Search
    }

}