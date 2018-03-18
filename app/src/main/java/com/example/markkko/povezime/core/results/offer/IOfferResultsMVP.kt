package com.example.markkko.povezime.core.results.offer

import com.example.markkko.povezime.core.base.BaseUserInteractor
import com.example.markkko.povezime.core.base.BaseUserPresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.*
import io.reactivex.Single


interface IOfferResultsMVP {

    interface View : BaseView {

        fun onRequestPosted(request: MatchInfo)

    }

    interface Presenter : BaseUserPresenter {

        var view: View

        fun getResults(): List<Search>

        fun postRequest(searchId: Long)
    }

    interface Interactor : BaseUserInteractor {

        fun getResults(): List<Search>

        fun postRequest(data: RequestReq) : Single<MatchInfo>

        fun getCurrentOffer(): Offer
    }

}