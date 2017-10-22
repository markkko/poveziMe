package com.example.markkko.povezime.core.login

import android.app.Activity

import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


interface LoginPresenter : BasePresenter{

    fun loginWithFirebase(activity: Activity, firebaseAuth: FirebaseAuth, email: String, password: String)
    fun sendInfoToServer(email: String, regId: String)

    interface View : BaseView {
        fun onLoginSuccess(firebaseUser: FirebaseUser)
        fun onLoginFail()
        fun onSendInfoSuccess(userData: UserData)
        fun onSendInfoFail()
    }

}
