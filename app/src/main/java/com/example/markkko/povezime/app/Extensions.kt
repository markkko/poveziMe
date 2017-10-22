package com.example.markkko.povezime.app

import com.example.markkko.povezime.app.PoveziMeApplication.Companion.component
import com.example.markkko.povezime.app.di.GoogleApiModule
import com.example.markkko.povezime.app.home.offer.OfferSubComponent
import com.example.markkko.povezime.app.home.search.SearchSubComponent
import com.example.markkko.povezime.app.login.LoginSubComponent
import com.example.markkko.povezime.core.home.offer.OfferModule
import com.example.markkko.povezime.core.home.search.SearchModule
import com.example.markkko.povezime.core.login.LoginModule

fun PoveziMeApplication.getLoginSubComponent(): LoginSubComponent {
    if (null == loginSubComponent)
        loginSubComponent = component!!.plus(LoginModule())

    return this.loginSubComponent!!
}
fun PoveziMeApplication.releaseLoginSubComponent() {
    loginSubComponent = null
}

fun PoveziMeApplication.getSearchSubComponent(): SearchSubComponent {
    if (null == searchSubComponent)
        searchSubComponent = PoveziMeApplication.component!!.plus(SearchModule(), GoogleApiModule())
    return searchSubComponent!!
}
fun PoveziMeApplication.releaseSeachSubComponent() {
    searchSubComponent = null
}

fun PoveziMeApplication.getOfferSubComponent(): OfferSubComponent {
    if (null == offerSubComponent)
        offerSubComponent = PoveziMeApplication.component!!.plus(OfferModule(), GoogleApiModule())
    return offerSubComponent!!
}
fun PoveziMeApplication.releaseOfferSubComponent() {
    offerSubComponent = null
}

