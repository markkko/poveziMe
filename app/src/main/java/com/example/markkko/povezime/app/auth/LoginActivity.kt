package com.example.markkko.povezime.app.auth

import android.content.SharedPreferences
import android.os.Bundle

import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.home.HomeActivity
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.auth.login.ILoginMVP
import com.example.markkko.povezime.core.models.User
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : BaseActivity(), ILoginMVP.View {

    override fun showMessage(message: String) {
    }
    override fun showOfflineMessage(isCritical: Boolean) {
    }

    override fun onLoginFail() {
        showToast(R.string.login_failed)
    }

    override fun onSendInfoSuccess(user: User) {
        if (!user.isCompletedInfo()) {
            navigateToActivity(CompleteInfoActivity::class.java, Bundle())
            finishAffinity()
        }
        navigateToActivity(HomeActivity::class.java, Bundle())
        finishAffinity()
    }

    override fun onSendInfoFail() {
    }

    /**********************
     * Fields
     *********************/

    @Inject
    lateinit var loginPresenter: ILoginMVP.Presenter

    @Inject
    lateinit var prefs: SharedPreferences

    /**********************
     * Callbacks
     *********************/

    /**********************
     * Setup
     *********************/

    override val layoutId: Int = R.layout.fragment_login

    override fun bind() {
        loginPresenter.view = this
    }

    override fun onResume() {
        super.onResume()
        tryToLoginAuto()
    }

    override fun subscribeToUIEvents() {
        RxView.clicks(btn_login)
                .throttleFirst(2, TimeUnit.SECONDS)
                .filter({
                    var valid = true
                    if (isNullOrEmpty(emailInput)) {
                        emailInput.error = getString(R.string.alert_field_cannot_be_empty)
                        valid = false
                    }
                    if (isNullOrEmpty(passwordInput)) {
                        passwordInput.error = getString(R.string.alert_field_cannot_be_empty)
                        valid = false
                    }
                    valid
                })
                .subscribe({
                    loginPresenter.loginWithFirebase(this,
                            emailInput.text.toString(),
                            passwordInput.text.toString())})

        RxView.clicks(btn_signup).subscribe({navigateToActivity(RegistrationActivity::class.java, Bundle())})
    }

    override fun injectDependencies() {
        injector.inject(this)
    }


    private fun tryToLoginAuto() {
        val email = prefs.getString(AppConstants.PREF_EMAIL, "")
        val regId = prefs.getString(AppConstants.PREF_REG_ID, "")
        if (!isNullOrEmpty(email)  && !isNullOrEmpty(regId)) {
            loginPresenter.sendInfoToServer(email, regId)
        }
    }
}
