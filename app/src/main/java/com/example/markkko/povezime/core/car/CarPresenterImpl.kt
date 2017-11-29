package com.example.markkko.povezime.core.car

import android.content.SharedPreferences
import com.example.markkko.povezime.app.user.UserService
import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class CarPresenterImpl @Inject constructor(private val carInteractor: CarInteractor,
                                           private val schedulerProvider: SchedulerProvider,
                                           private val prefs: SharedPreferences): CarPresenter {

    override var disposables: CompositeDisposable = CompositeDisposable()

    override lateinit var view: CarPresenter.View

    override fun addCar(car: Car) {
        rxTransaction {
            carInteractor.newCar(car)
                    .doOnSuccess { UserService.updateUser(prefs, it)  }
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({view.onCarAdded()}, {t -> t.message?.let { view.showMessage(it) } })
        }
    }

    override fun clear() {
    }

}