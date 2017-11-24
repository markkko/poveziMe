package com.example.markkko.povezime.app.auth


import android.support.v4.app.Fragment
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.base.views.BaseFragmentedActivity

class CompleteInfoActivity : BaseFragmentedActivity() {

    override fun getFragment(): Fragment =
            CompleteInfoFragment.newInstance()

}
