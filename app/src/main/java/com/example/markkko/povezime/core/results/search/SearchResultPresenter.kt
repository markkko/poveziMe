package com.example.markkko.povezime.core.results.search

import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.SearchRequestReq
import com.example.markkko.povezime.core.models.Search
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

    override fun postRequest(data: SearchRequestReq) {
        interactor.postRequest(data)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe({view.onRequestPosted(it)},{})
    }

    override fun me(): User = interactor.me()

    override fun clear() {}
}