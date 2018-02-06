package com.example.markkko.povezime.app.auth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.base.views.showToast
import com.example.markkko.povezime.app.home.HomeActivity
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.core.auth.login.ILoginMVP
import com.example.markkko.povezime.core.models.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class LoginActivity : BaseActivity(), ILoginMVP.View {

    private val RC_SIGN_IN = 100

    private lateinit var mGoogleSignInClient: GoogleSignInClient

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    /**********************
     * Setup
     *********************/

    override val layoutId: Int = R.layout.fragment_login

    override fun bind() {
        loginPresenter.view = this
    }

    override fun prepareData() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onResume() {
        super.onResume()
        tryToLoginAuto()
    }

    override fun subscribeToUIEvents() {
        googleLogin.setOnClickListener{signInGoogle()}
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

    /**********************
     * Internal
     *********************/

    private fun tryToLoginAuto() {
        val email = prefs.getString(AppConstants.PREF_EMAIL, "")
        val regId = prefs.getString(AppConstants.PREF_REG_ID, "")
        if (!isNullOrEmpty(email)  && !isNullOrEmpty(regId)) {
            loginPresenter.sendInfoToServer(email, regId)
        }
    }

    private fun signInGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val regId = prefs.getString(AppConstants.PREF_REG_ID, "")
            val email = account.email
            prefs.edit().putString(AppConstants.PREF_EMAIL, account.email).apply()
            if (!isNullOrEmpty(email) && !isNullOrEmpty(regId)) {
                loginPresenter.sendInfoToServer(account.email!!, regId)
            }
        } catch (e: ApiException) {
        }
    }
}
