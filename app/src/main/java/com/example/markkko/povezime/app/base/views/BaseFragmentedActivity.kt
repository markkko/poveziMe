package com.example.markkko.povezime.app.base.views

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication


abstract class BaseFragmentedActivity: BaseActivity() {


    override val layoutId: Int = R.layout.activity_base_fragmented

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(baseLayoutId)
        setupActionBar()
        layoutInflater.inflate(layoutId,  findViewById(R.id.frame_holder))

        setupFragments()

        createAndSubscribe()
    }

    open protected fun setupFragments() {
        val fragment = supportFragmentManager.findFragmentByTag("main_fragment")
        if (fragment == null) {
            attachFragments()
        }
    }

    private fun createAndSubscribe() {
        bind()
        prepareData()
        subscribeToUIEvents()
    }

    open protected fun attachFragments() {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, getFragment(), "main_fragment")
                .commit()
    }

    abstract fun getFragment(): Fragment

    override fun injectDependencies(application: PoveziMeApplication) {

    }
}