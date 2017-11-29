package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.core.base.*
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single


interface IOfferMVP {

    interface View: BaseView {

        fun onOfferSuccess(results: List<OfferResult>)

    }

    interface Presenter : BaseUserPresenter {

        var view: View?

        fun getRoute(addresses: Array<LatLng?>)

        fun offerRide(offer: OfferRequest)

    }

    interface Interactor: BaseUserInteractor {

        fun offerRide(offer: OfferRequest) : Single<List<OfferResult>>

    }

}
