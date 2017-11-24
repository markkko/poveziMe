package com.example.markkko.povezime.core.auth.registration


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RegistrationPresenter @Inject constructor()
    : IRegistrationMVP.Presenter {


    override lateinit var view: IRegistrationMVP.View
    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun registerOnFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) view.onRegistrationComplete(task.result)
                    else  {
                        Log.d("thr_reg", task.exception?.message)
                        view.onRegistrationFailed()
                    }
                }
    }

    override fun clear() {
    }
}
