package com.example.markkko.povezime.core.auth.registration


import android.content.SharedPreferences
import android.util.Log
import com.example.markkko.povezime.app.util.AppConstants
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(private val prefs:SharedPreferences)
    : IRegistrationMVP.Presenter {


    override lateinit var view: IRegistrationMVP.View
    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun registerOnFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)  {
                        prefs.edit().putString(AppConstants.PREF_EMAIL, task.result.user.email).apply()
                        view.onRegistrationComplete(task.result)
                    }
                    else  {
                        Log.d("thr_reg", task.exception?.message)
                        view.onRegistrationFailed()
                    }
                }
    }
}
