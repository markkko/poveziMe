package com.example.markkko.povezime.core.home.search

import android.util.Log
import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

class SearchPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                          private val searchInteractor: ISearchMVP.Interactor) : ISearchMVP.Presenter {

    override var disposables: CompositeDisposable = CompositeDisposable()

    override lateinit var view: ISearchMVP.View

    override fun getSearchResults(data: Search) {
        rxTransaction {
            searchInteractor.getSearchResults(data)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({ view.showResults(it) }) { t ->
                        Log.d("search_thr", t.message)
                        view.showMessage("Server problem") }
        }
    }

    override fun me(): User = searchInteractor.me()
}
