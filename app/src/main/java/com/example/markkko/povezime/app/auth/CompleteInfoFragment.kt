package com.example.markkko.povezime.app.auth

import android.os.Bundle
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseFragment
import com.example.markkko.povezime.app.base.views.navigateToActivityAndClearStackWithExtras
import com.example.markkko.povezime.app.home.HomeActivity
import com.example.markkko.povezime.app.user.UserService
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.app.util.isValidPhoneNumber
import com.example.markkko.povezime.core.auth.completeInfo.ICompleteInfoMVP
import com.example.markkko.povezime.core.models.User
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_complete_info.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class CompleteInfoFragment: BaseFragment(), ICompleteInfoMVP.View  {

    override val layoutId: Int = R.layout.activity_complete_info

    /**********************
     * Fields
     *********************/

    @Inject
    lateinit var presenter: ICompleteInfoMVP.Presenter

    /**********************
     * Callbacks
     *********************/

    override fun showMessage(message: String) {}

    override fun showOfflineMessage(isCritical: Boolean) {}

    override fun onInfoCompleted(user: User) {
        UserService.user = user
        navigateToActivityAndClearStackWithExtras(HomeActivity::class.java, Bundle())
        activity.finish()
    }

    /**********************
     * Setup
     *********************/

    override fun injectDependencies(application: PoveziMeApplication) {
        application.activityComponent().inject(this)
    }

    override fun subscribeForUIEvents() {
        RxView.clicks(next)
                .throttleFirst(2, TimeUnit.SECONDS)
                .filter({
                    var valid = true
                    if (isNullOrEmpty(name)) {
                        name.error = "Molimo unesite vase ime"
                        valid = false
                    }
                    if (isNullOrEmpty(surname)) {
                        surname.error = "Molimo unesite vase prezime"
                        valid = false
                    }
                    if (!isValidPhoneNumber(phone.text)) {
                        phone.error = "Molimo unesite ispravan broj"
                        valid = false
                    }
                    valid
                })
                .subscribe({presenter.completeInfo(createUser())})
    }

    override fun bind() {
        presenter.view = this
    }

    /**********************
     * Inernal
     *********************/

    private fun createUser(): User {
        val user = UserService.user.makeDeepCopy()
        user.name = name.text.toString()
        user.surname = surname.text.toString()
        user.phone = phone.text.toString()
        user.viber = if (viber.isChecked) 1 else 0
        user.whatsapp = if (whatsapp.isChecked) 1 else 0
        return user
    }

    companion object {
        fun newInstance(): CompleteInfoFragment = CompleteInfoFragment()
    }
}