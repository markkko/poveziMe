package com.example.markkko.povezime.app.auth

import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseFragment
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.util.isNullOrEmpty
import com.example.markkko.povezime.app.util.isValidEmail
import com.example.markkko.povezime.core.auth.registration.IRegistrationMVP
import com.google.firebase.auth.AuthResult
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_registration.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RegistrationFragment : BaseFragment(), IRegistrationMVP.View {

    override val layoutId: Int = R.layout.activity_registration

    /**********************
     * Fields
     *********************/

    @Inject
    lateinit var presenter: IRegistrationMVP.Presenter

    /**********************
     * Callbacks
     *********************/

    override fun showMessage(message: String) {}

    override fun showOfflineMessage(isCritical: Boolean) {}

    override fun onRegistrationComplete(results: AuthResult) {
        baseActivity.navigateToActivity(LoginActivity::class.java)
        activity.finishAffinity()
    }

    override fun onRegistrationFailed() {
        email.error = "Korisnik vec postoji u bazi"
    }

    /**********************
     * Setup
     *********************/

    override fun subscribeForUIEvents() {
        RxView.clicks(signUp)
                .throttleFirst(2, TimeUnit.SECONDS)
                .filter({
                    var valid = true
                    if (!isValidEmail(email)) {
                        email.error = "Neispravan mail"
                        valid = false
                    }
                    if (isNullOrEmpty(password)) {
                        password.error = "Neispravna sifra"
                        valid = false
                    }
                    valid
                })
                .subscribe({ presenter.registerOnFirebase(email.text.toString(), password.text.toString()) })
    }

    override fun injectDependencies() {
        baseActivity.injector.inject(this)
    }

    override fun bind() {
        presenter.view = this
    }

    companion object {
        fun newInstance() = RegistrationFragment()
    }

}