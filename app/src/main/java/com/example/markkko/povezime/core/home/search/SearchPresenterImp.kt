package com.example.markkko.povezime.core.home.search

import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.SearchRequestData
import com.example.markkko.povezime.core.util.GeocoderUtils
import com.example.markkko.povezime.core.util.RXUtil
import com.example.markkko.povezime.core.util.SchedulerProvider

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

class SearchPresenterImp @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                             private val searchInteractor: SearchInteractor,
                                             private val geocoderUtils: GeocoderUtils) : SearchPresenter {

    override var disposables: CompositeDisposable = CompositeDisposable()

    override var view: SearchPresenter.View? = null



    override fun getSearchResults(data: SearchRequestData) {
        rxTransaction {
            searchInteractor.getSearchResults(data)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({ view!!.showResults(it) }) { throwable -> view!!.showMessage("Server problem") }
        }
    }

    override fun clear() {
    }
}
