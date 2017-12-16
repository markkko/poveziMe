package com.example.markkko.povezime.core.requests

import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class AllRequestsPresenter @Inject constructor(private val interactor: IRequestsMVP.Interactor,
                           private val schedulerProvider: SchedulerProvider): IRequestsMVP.Presenter {

    override var disposables: CompositeDisposable = CompositeDisposable()
    override lateinit var view: IRequestsMVP.View

    override fun getAllRequests() {
        interactor.getAllRequests()
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe({view.onRequestsFetched(it)}, {})

    }
}