package com.example.markkko.povezime.app.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.auth.LoginActivity
import com.example.markkko.povezime.app.base.views.BaseActivity
import com.example.markkko.povezime.app.base.views.navigateToActivity
import com.example.markkko.povezime.app.car.AddCarActivity
import com.example.markkko.povezime.app.home.offer.OfferFragment
import com.example.markkko.povezime.app.home.search.SearchFragment
import com.example.markkko.povezime.app.profile.ProfileActivity
import com.example.markkko.povezime.app.requests.RequestsActivity
import com.example.markkko.povezime.core.home.IHomeMVP
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), IHomeMVP.View {

    /**********************
     * Fields
     ***********************/

    @Inject lateinit var presenter: IHomeMVP.Presenter

    private val mSectionsPagerAdapter: SectionsPagerAdapter by lazy {
        SectionsPagerAdapter(supportFragmentManager)
    }

    override val layoutId: Int = 0

    /**********************
     * Callbacks
     ***********************/

    override fun showMessage(message: String) {}

    override fun showOfflineMessage(isCritical: Boolean) {}

    override fun onLoggedOut() {
        navigateToActivity(LoginActivity::class.java)
        finishAffinity()
    }

    /**********************
     * Setup
     ***********************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)

        injectDependencies()

        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        presenter.view = this
    }

    override fun injectDependencies() {
        injector.inject(this)
    }

    override fun create(savedInstanceState: Bundle?) {}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_profile) {
            navigateToActivity(ProfileActivity::class.java)
            return true
        }
        if (id == R.id.action_requests) {
            navigateToActivity(RequestsActivity::class.java)
        }
        if (id == R.id.action_add_car) {
            navigateToActivity(AddCarActivity::class.java)
            return true
        }
        if (id == R.id.action_logout) {
            presenter.logout()
        }

        return false
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {

            return if (position == 0) SearchFragment.newInstance()
            else OfferFragment.newInstance()
        }

        override fun getCount(): Int = 2
    }

}
