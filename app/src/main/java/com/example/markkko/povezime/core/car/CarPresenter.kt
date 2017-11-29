package com.example.markkko.povezime.core.car

import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.Car


interface CarPresenter: BasePresenter {

    var view: View

    fun addCar(car: Car)

    interface View: BaseView {
        fun onCarAdded()
    }

}
