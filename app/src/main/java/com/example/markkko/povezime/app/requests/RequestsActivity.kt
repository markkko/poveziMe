package com.example.markkko.povezime.app.requests

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.base.views.BaseFragmentedActivity
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.car.AddCarActivity
import com.example.markkko.povezime.app.home.offer.OfferFragment
import com.example.markkko.povezime.app.home.search.SearchFragment
import com.example.markkko.povezime.app.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_home.*

class RequestsActivity : BaseFragmentedActivity() {

    override fun getFragment(): Fragment =
            AllRequestsFragment.newInstance()
}
