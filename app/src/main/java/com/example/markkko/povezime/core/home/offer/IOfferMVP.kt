package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.core.base.*
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.OfferResultsReq
import com.example.markkko.povezime.core.models.Route
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single


interface IOfferMVP {

    interface View: BaseView {

        fun onOfferSuccess(results: List<Offer>)

        fun onRouteFetched(route: Route)

    }

    interface Presenter : BaseUserPresenter {

        var view: View

        var route: Route?

        fun getRoute(addresses: Array<LatLng?>)

        fun offerRide(offer: OfferResultsReq)

        fun getCars(): List<Car>

    }

    interface Interactor: BaseUserInteractor {

        fun offerRide(offer: OfferResultsReq) : Single<List<Offer>>

        fun getRoute(addresses: Array<LatLng?>): Single<String>
    }

}
