package com.example.markkko.povezime.core.home.offer

import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.OfferRequest
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class OfferPresenter @Inject constructor(private val offerInteractor: OfferInteractor,
                                         private val schedulerProvider: SchedulerProvider)
    : IOfferMVP.Presenter{


    override var view: IOfferMVP.View? = null

    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun getRoute(addresses: Array<LatLng?>) {

    }

    override fun offerRide(offer: OfferRequest) {
        rxTransaction {
            offerInteractor.offerRide(offer)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({view?.onOfferSuccess(it)}, {view?.showMessage(it.message!!)})
        }
    }

    override fun me(): User = offerInteractor.me()

    override fun clear() {

    }
}