package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.OfferResult
import com.google.android.gms.maps.model.LatLng


interface OfferPresenter: BasePresenter {

    var view: View?

    fun getRoute(addresses: Array<LatLng?>)

    fun offerRide(offer: OfferRequest)


    interface View: BaseView {

        fun onOfferSuccess(results: List<OfferResult>)
    }

}