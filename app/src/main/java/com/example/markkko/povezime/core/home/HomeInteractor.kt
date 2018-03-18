package com.example.markkko.povezime.core.home

import android.content.SharedPreferences
import com.example.markkko.povezime.app.util.AppConstants
import com.example.markkko.povezime.core.repositoreis.OfferRepository
import com.example.markkko.povezime.core.repositoreis.SearchRepository
import javax.inject.Inject


class HomeInteractor @Inject constructor(val searchRepository: SearchRepository,
                                         val offerRepository: OfferRepository,
                                         val prefs: SharedPreferences): IHomeMVP.Interactor {

    override fun logout(): Boolean {
        searchRepository.clear()
        offerRepository.clear()
        prefs.edit().putString(AppConstants.PREF_EMAIL, "").apply()
        return true
    }
}