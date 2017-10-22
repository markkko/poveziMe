package com.example.markkko.povezime.core.login

import android.app.Activity

import com.example.markkko.povezime.core.base.BasePresenter
import com.example.markkko.povezime.core.base.BaseView
import com.example.markkko.povezime.core.models.dto.UserDTO


interface LoginPresenter : BasePresenter{

    var view: View

    fun loginWithFirebase(activity: Activity, email: String, password: String)
    fun sendInfoToServer(email: String, regId: String)

    interface View : BaseView {
        fun onLoginFail()
        fun onSendInfoSuccess(userDTO: UserDTO)
        fun onSendInfoFail()
    }

}
