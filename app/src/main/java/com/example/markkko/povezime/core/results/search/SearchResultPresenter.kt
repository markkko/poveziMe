package com.example.markkko.povezime.core.results.search

import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.RequestReq
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class SearchResultPresenter @Inject constructor(private val interactor: ISearchResultsMVP.Interactor,
                                                private val schedulerProvider: SchedulerProvider)
    : ISearchResultsMVP.Presenter {

    override var disposables: CompositeDisposable = CompositeDisposable()

    override lateinit var view: ISearchResultsMVP.View

    override fun getResults(): List<Offer> = interactor.getResults()

    override fun postRequest(offerId: Long) {
        val request = RequestReq(userId = me().id, searchId = interactor.getCurrentSearch().id, offerId = offerId)
        interactor.postRequest(request)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe({view.onRequestPosted(it)},{})
    }

    override fun me(): User = interactor.me()
}