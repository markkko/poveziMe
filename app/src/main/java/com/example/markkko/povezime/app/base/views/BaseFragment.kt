package com.example.markkko.povezime.app.base.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.markkko.povezime.app.PoveziMeApplication


abstract class BaseFragment : android.support.v4.app.Fragment() {

    protected abstract var layoutId: Int

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(layoutId, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareData()
        subscribeForUIEvents()
    }

    open fun subscribeForUIEvents() {}

    open fun prepareData(){}

    abstract fun bind()

    open fun load(){}

    override fun onResume() {
        super.onResume()
        bind()
        load()
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        injectDependencies(PoveziMeApplication[getContext()])

    }

    protected abstract fun injectDependencies(application: PoveziMeApplication)

}