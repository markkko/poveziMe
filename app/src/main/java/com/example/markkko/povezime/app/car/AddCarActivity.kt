package com.example.markkko.povezime.app.car

import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.car.CarPresenter
import com.example.markkko.povezime.core.models.Car
import com.example.markkko.povezime.core.models.User
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_add_car.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class AddCarActivity : BaseActivity(), CarPresenter.View {

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun showOfflineMessage(isCritical: Boolean) {
    }

    override fun onCarAdded() {
        showToast(R.string.car_added_successfully)
    }

    /**********************
     * Fields
     **********************/

    @Inject
    lateinit var carPresenter: CarPresenter

    @Inject
    @field:Named("me")
    lateinit var me: User

    /**********************
     * Setup
     **********************/

    override val layoutId: Int = R.layout.activity_add_car

    override fun subscribeToUIEvents() {
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
                    valid }
                .subscribe {valid -> carPresenter.addCar(
                        Car(brand = brand.text.toString(),
                                model = subline.text.toString(),
                                seats = cap.text.toString().toInt(),
                                email = me.email))}
    }

    override fun bind() {
        carPresenter.view = this
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        //application.getCarSubComponent().inject(this)
    }

    override fun releaseSubComponents(application: PoveziMeApplication) {
        //application.releaseCarSubComponent()
    }

}
