package com.example.markkko.povezime.app.car

import android.support.v4.app.Fragment
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseFragment
import com.example.markkko.povezime.app.base.views.BaseFragmentedActivity
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.car.ICarMVP
import com.example.markkko.povezime.core.models.Car
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_add_car.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddCarActivity : BaseFragmentedActivity() {

    override fun getFragment(): Fragment = AddCarFragment.newInstance()

    class AddCarFragment : BaseFragment(), ICarMVP.View {

        override fun showMessage(message: String) {
            baseActivity.showToast(message)
        }

        override fun showOfflineMessage(isCritical: Boolean) {
        }

        override fun onCarAdded() {
            baseActivity.showToast(R.string.car_added_successfully)
            activity.finish()
        }

        /**********************
         * Fields
         **********************/

        @Inject
        lateinit var presenter: ICarMVP.Presenter

        /**********************
         * Setup
         **********************/

        override val layoutId: Int = R.layout.fragment_add_car

        override fun prepareData() {
            activity.title = getString(R.string.add)
        }

        override fun subscribeForUIEvents() {

            val me = presenter.me()

            RxView.clicks(btnAdd).throttleFirst(2, TimeUnit.SECONDS)
                    .filter {
                        var valid = true
                        if (isNullOrEmpty(brand)) {
                            brand.error = getString(R.string.alert_field_cannot_be_empty)
                            valid = false
                        }
                        if (isNullOrEmpty(cap)) {
                            cap.error = getString(R.string.alert_field_cannot_be_empty)
                            valid = false
                        }
                        valid
                    }
                    .subscribe { _ ->
                        presenter.addCar(
                                Car(make = brand.text.toString(),
                                        model = subline.text.toString(),
                                        seats = cap.text.toString().toInt(),
                                        userId = me.id,
                                        email = me.email))
                    }
        }


        override fun bind() {
            presenter.view = this
        }

        override fun injectDependencies() {
            baseActivity.injector.inject(this)
        }

        companion object {
            fun newInstance() = AddCarFragment()
        }
    }
}
