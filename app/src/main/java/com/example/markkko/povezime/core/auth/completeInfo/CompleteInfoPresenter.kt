package com.example.markkko.povezime.core.auth.completeInfo

import android.util.Log
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class CompleteInfoPresenter @Inject constructor(private val interactor: CompleteInfoInteractor,
                                                private val schedulerProvider: SchedulerProvider):
        ICompleteInfoMVP.Presenter {


    override lateinit var view: ICompleteInfoMVP.View

    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun completeInfo(user: User) {
        interactor.updateUser(user)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe(
                        {view.onInfoCompleted(it)},
                        {t -> Log.d("thr_cInfo", t.message)})
    }

    override fun me(): User = interactor.me()

    override fun clear() {

    }
}
