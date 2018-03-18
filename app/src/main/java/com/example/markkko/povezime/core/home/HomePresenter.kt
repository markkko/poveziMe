package com.example.markkko.povezime.core.home

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomePresenter @Inject constructor(val homeIneractor: IHomeMVP.Interactor): IHomeMVP.Presenter {

    override lateinit var view: IHomeMVP.View

    override var disposables = CompositeDisposable()

    override fun logout() {
        if (homeIneractor.logout()) {
            view.onLoggedOut()
        }
    }

}
