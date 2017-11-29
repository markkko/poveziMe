package com.example.markkko.povezime.app.base.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.ButterKnife
import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication


abstract class BaseActivity : AppCompatActivity() {

    open val baseLayoutId: Int = R.layout.activity_base

    protected abstract val layoutId: Int

    var newInstance = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(baseLayoutId)
        setupActionBar()
        layoutInflater.inflate(layoutId,  findViewById(R.id.frame_holder))
        ButterKnife.bind(this)
        injectDependencies(PoveziMeApplication[this])

        savedInstanceState?.let { newInstance = false }

        createAndSubscribe()
    }

    private fun createAndSubscribe() {
        bind()
        prepareData()
        subscribeToUIEvents()
    }

    open fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.elevation = 0F
            //Back button enabled
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }
    }

   /* open fun getToolbar(): Toolbar {
        return findViewById(R.id.toolbar)
    }*/

    open fun prepareData() {}

    open fun bind(){}

    open fun unbind(){}

    open fun subscribeToUIEvents() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
        releaseSubComponents(PoveziMeApplication[this])
    }

    override fun onResume() {
        super.onResume()
        bind()
    }

    override fun onStop() {
        unbind()
        super.onStop()
    }

    open protected fun injectDependencies(application: PoveziMeApplication){}

    open protected fun releaseSubComponents(application: PoveziMeApplication){}

}
