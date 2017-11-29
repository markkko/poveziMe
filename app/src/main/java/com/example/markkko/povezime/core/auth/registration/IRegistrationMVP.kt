package com.example.markkko.povezime.core.auth.registration

import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.google.firebase.auth.AuthResult


interface IRegistrationMVP {

    interface View : BaseView {
        fun onRegistrationComplete(results: AuthResult)
        fun onRegistrationFailed()
    }

    interface Presenter : BasePresenter {

        var view: View

        fun registerOnFirebase(email: String, password: String)
    }

}
