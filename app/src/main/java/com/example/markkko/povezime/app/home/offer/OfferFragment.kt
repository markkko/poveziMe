package com.example.markkko.povezime.app.home.offer

import com.example.markkko.povezime.R
import com.example.markkko.povezime.app.PoveziMeApplication
import com.example.markkko.povezime.app.getOfferSubComponent
import com.example.markkko.povezime.app.home.BaseHomeFragment
import com.example.markkko.povezime.core.home.offer.OfferPresenter
import com.example.markkko.povezime.core.models.OfferResult
import javax.inject.Inject


class OfferFragment: BaseHomeFragment(), OfferPresenter.View {

    override fun showMessage(message: String) {

    }

    override fun showOfflineMessage(isCritical: Boolean) {

    }

    override fun onOfferSuccess(results: List<OfferResult>) {

    }

    /**********************
     * Fields
     ***********************/

    @Inject
    lateinit var offerPresenter: OfferPresenter

    /**********************
     * Setup
     ***********************/

    override var layoutId: Int = R.layout.fragment_offer

    override fun bind() {
        offerPresenter.view = this
    }

    override fun injectDependencies(application: PoveziMeApplication) {
        application.getOfferSubComponent().inject(this)
    }

    /**********************
     * Internal
     ***********************/

}