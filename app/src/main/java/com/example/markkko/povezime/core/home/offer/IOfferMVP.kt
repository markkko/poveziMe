package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.core.base.BaseUserInteractor
import com.example.markkko.povezime.core.base.BaseUserPresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Route
import com.example.markkko.povezime.core.models.Search
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single


interface IOfferMVP {

    interface View : BaseView {

        fun onOfferSuccess(results: List<Search>)

        fun onRouteFetched(route: Route)

    }

    interface Presenter : BaseUserPresenter {

        var view: View

        var route: Route?

        fun getRoute(addresses: Array<LatLng?>)

        fun offerRide(offer: Offer)

        fun getCars(): List<Car>

    }

    interface Interactor : BaseUserInteractor {

        fun offerRide(offer: Offer): Single<List<Search>>

        fun getRoute(addresses: Array<LatLng?>): Single<String>
    }

}
