package com.example.markkko.povezime.core.auth.registration

import android.app.Activity
import com.example.markkko.povezime.core.auth.login.LoginPostData
import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.dto.UserDTO
import com.google.firebase.auth.AuthResult
import io.reactivex.Single


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
