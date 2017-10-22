package com.example.markkko.povezime.core.login


import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.base.rxTransaction

import com.example.markkko.povezime.core.util.SchedulerProvider
import com.google.firebase.auth.FirebaseAuth

import javax.inject.Inject

import io.reactivex.disposables.CompositeDisposable

class LoginPresenterImpl @Inject constructor(private val schedulerProvider: SchedulerProvider,
                                             private val loginInteractor: LoginInteractor,
                                             private val prefs: SharedPreferences) : LoginPresenter {


    override lateinit var view: LoginPresenter.View
    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun loginWithFirebase(activity: Activity, email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity) { task ->
                    if (!task.isSuccessful) {
                        view.onLoginFail()
                    } else {
                        sendInfoToServer(email,
                                prefs.getString(AppConstants.SHARED_PREF_REG_ID, ""))
                    }
                }
    }

    override fun sendInfoToServer(email: String, regId: String) {
        val data = LoginPostData()
        data.email = "marko.m@gmail.com"
        data.regId = "1234567890"

        rxTransaction {
            loginInteractor.sendLoginInfoToServer(data)
                    .subscribeOn(schedulerProvider.backgroundThread())
                    .observeOn(schedulerProvider.mainThread())
                    .subscribe({ view.onSendInfoSuccess(it) })
                    { throwable -> Log.d("www", throwable.toString()) }
        }


    }

    override fun clear() {
    }
}
