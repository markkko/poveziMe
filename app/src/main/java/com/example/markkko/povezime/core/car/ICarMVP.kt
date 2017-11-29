package com.example.markkko.povezime.core.car

import com.example.markkko.povezime.core.base.*
import com.example.markkko.povezime.core.models.Car
import io.reactivex.Single


interface ICarMVP {

    interface View: BaseView {

        fun onCarAdded()

    }

    interface Presenter: BaseUserPresenter {

        var view: View

        fun addCar(car: Car)

    }

    interface Interactor: BaseUserInteractor {

        fun newCar(car: Car): Single<Car>

    }

}