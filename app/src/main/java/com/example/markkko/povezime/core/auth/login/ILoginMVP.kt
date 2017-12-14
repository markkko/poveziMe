package com.example.markkko.povezime.core.auth.login

import android.app.Activity
import com.example.markkko.povezime.core.base.BaseInteractor
import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.User
import io.reactivex.Single


interface ILoginMVP {

    interface View : BaseView {
        fun onLoginFail()
        fun onSendInfoSuccess(user: User)
        fun onSendInfoFail()
    }

    interface Presenter : BasePresenter {

        var view: View

        fun loginWithFirebase(activity: Activity, email: String, password: String)
        fun sendInfoToServer(email: String, regId: String)
    }


    interface Interactor : BaseInteractor {
        fun sendLoginInfoToServer(data: LoginPostData): Single<User>
    }
}
