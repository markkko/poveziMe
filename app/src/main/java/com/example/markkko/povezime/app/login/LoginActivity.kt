package com.example.markkko.povezime.app.login

import android.content.SharedPreferences
import android.os.Bundle

import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.base.views.navigateToActivityAndClearStackWithExtras
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.getLoginSubComponent
import com.example.markkko.povezime.app.home.HomeActivity
import com.example.markkko.povezime.app.releaseLoginSubComponent
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.app.util.StringUtils
import com.example.markkko.povezime.core.login.LoginPresenter
import com.example.markkko.povezime.core.models.dto.UserDTO
import com.google.gson.Gson
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginPresenter.View {

    override fun showMessage(message: String) {
    }
    override fun showOfflineMessage(isCritical: Boolean) {
    }

    override fun onLoginFail() {
        showToast(R.string.login_failed)
    }

    override fun onSendInfoSuccess(userDTO: UserDTO) {
        navigateToActivityAndClearStackWithExtras(HomeActivity::class.java, Bundle())
        finish()
    }

    override fun onSendInfoFail() {
    }

    /**********************
     * Fields
     *********************/

    @Inject
    lateinit var loginPresenter: LoginPresenter

    @Inject
    lateinit var prefs: SharedPreferences

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
                    loginPresenter.loginWithFirebase(this,
                            emailInput.text.toString(),
                            passwordInput.text.toString())})
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        application.getLoginSubComponent().inject(this)
    }

    override fun releaseSubComponents(application: PoveziMeApplication) {
        application.releaseLoginSubComponent()
    }

    private fun tryToLoginAuto() {
        val json = prefs.getString(AppConstants.SHARED_PREF_USER, "")
        val regId = prefs.getString(AppConstants.SHARED_PREF_REG_ID, "")
        if (json != "" && regId != "") {
            val user = Gson().fromJson(json, UserDTO::class.java)
            loginPresenter.sendInfoToServer(user.email, regId)
        }
    }
}
