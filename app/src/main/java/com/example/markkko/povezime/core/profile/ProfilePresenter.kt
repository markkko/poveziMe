package com.example.markkko.povezime.core.profile

import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ProfilePresenter @Inject constructor(private val interactor: ProfileInteractor,
                       private val schedulerProvider: SchedulerProvider) : IProfileMVP.Presenter {

    override var disposables: CompositeDisposable = CompositeDisposable()

    override lateinit var view: IProfileMVP.View

    override fun updateUser(data: User) {
        interactor.updateUser(data)
                .subscribeOn(schedulerProvider.backgroundThread())
                .observeOn(schedulerProvider.mainThread())
                .subscribe({view.onUserUpdated(it)}, {})
    }

    override fun me(): User = interactor.me()
}