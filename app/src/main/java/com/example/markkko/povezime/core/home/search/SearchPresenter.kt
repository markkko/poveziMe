package com.example.markkko.povezime.core.home.search

import android.util.Log
import com.example.markkko.povezime.R.string.offer
import com.example.markkko.povezime.R.string.wait
import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.Thread.sleep
import java.util.logging.Handler
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                          private val searchInteractor: ISearchMVP.Interactor) : ISearchMVP.Presenter {

    override var disposables: CompositeDisposable = CompositeDisposable()

    override lateinit var view: ISearchMVP.View

    override fun getSearchResults(data: Search) {
        /*(0..700).forEach {
            offerSignleRide(data)
            sleep(150)
        }*/
        // Pozivamo model koji nam vraća Observable
        searchInteractor.getSearchResults(data)
                // Govorimo Observable na kojoj niti
                // da izvrši naredbu. Mora da bude UI nit
                // jer vršimo izmene u view-u
                .observeOn(schedulerProvider.mainThread())
                // Pretplaćujemo se na Observable i
                // definišemo šta da uradimo kada podaci stignu
                .subscribe({ view.showResults(it) }) { t ->
                    view.showMessage("Network problem")
                }
    }

    private fun offerSignleRide(search: Search) {
        rxTransaction {
            searchInteractor.getSearchResults(search)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({ view.showResults(it) }, { view.showMessage(it.message!!) })
        }
    }

    override fun me(): User = searchInteractor.me()
}
