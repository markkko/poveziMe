package com.example.markkko.povezime.core.car

import android.content.SharedPreferences
import com.example.markkko.povezime.app.user.UserService
import com.example.markkko.povezime.core.base.rxTransaction
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class CarPresenter @Inject constructor(private val carInteractor: ICarMVP.Interactor,
                                       private val schedulerProvider: SchedulerProvider,
                                       private val prefs: SharedPreferences) : ICarMVP.Presenter{

    override var disposables: CompositeDisposable = CompositeDisposable()

    override lateinit var view: ICarMVP.View

    override fun addCar(car: Car) {
        rxTransaction {
            carInteractor.newCar(car)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({view.onCarAdded()}, {t -> t.message?.let { view.showMessage(it) } })
        }
    }

    override fun me(): User = carInteractor.me()
}