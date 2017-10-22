package com.example.markkko.povezime.app.login

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.text.TextUtils

import com.example.markkko.povezime.R
import com.example.markkko.povezime.R.string.email
import com.example.markkko.povezime.R.string.password
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.di.app.ApplicationComponent
import com.example.markkko.povezime.app.getLoginSubComponent
import com.example.markkko.povezime.app.releaseLoginSubComponent
import com.example.markkko.povezime.app.util.StringUtils
import com.example.markkko.povezime.core.login.LoginPresenter
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : BaseActivity() {

    /**********************
     * Fields
     *********************/

    @Inject
    lateinit var loginPresenter: LoginPresenter

    /**********************
     * Setup
     *********************/

    override val layoutId: Int
        get() = R.layout.fragment_login


    override fun subscribeToUIEvents() {
        RxView.clicks(btn_login)
                .throttleFirst(2, TimeUnit.SECONDS)
                .filter({
                    var valid = true
                    if (StringUtils.isNullOrEmpty(emailInput)) {
                        emailInput.error = getString(R.string.alert_field_cannot_be_empty)
                        valid = false
                    }
                    if (StringUtils.isNullOrEmpty(passwordInput)) {
                        passwordInput.error = getString(R.string.alert_field_cannot_be_empty)
                        valid = false
                    }
                    valid
                })
                .subscribe({
                    loginPresenter.loginWithFirebase(this, FirebaseAuth.getInstance(),
                        emailInput.text.toString(), passwordInput.text.toString())})
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        application.getLoginSubComponent().inject(this)
    }

    override fun releaseSubComponents(application: PoveziMeApplication) {
        application.releaseLoginSubComponent()
    }

}
