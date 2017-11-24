package com.example.markkko.povezime.app.auth

import android.support.v4.app.Fragment
import com.example.markkko.povezime.app.base.views.BaseFragmentedActivity

class RegistrationActivity : BaseFragmentedActivity() {

    override fun getFragment(): Fragment =
            RegistrationFragment.newInstance()
}
