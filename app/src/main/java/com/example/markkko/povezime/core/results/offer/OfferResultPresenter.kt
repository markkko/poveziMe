package com.example.markkko.povezime.core.results.offer

import com.example.markkko.povezime.core.models.RequestReq
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class OfferResultPresenter @Inject constructor(private val interactor: IOfferResultsMVP.Interactor,
                                               private val schedulerProvider: SchedulerProvider)
    : IOfferResultsMVP.Presenter {

    override var disposables: CompositeDisposable = CompositeDisposable()

    override lateinit var view: IOfferResultsMVP.View

    override fun getResults(): List<Search> = interactor.getResults()

    override fun postRequest(searchId: Long) {
        val request = RequestReq(userId = me().id, searchId = searchId, offerId = interactor.getCurrentOffer().id)
        interactor.postRequest(request)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe({view.onRequestPosted(it)},{})
    }

    override fun me(): User = interactor.me()
}