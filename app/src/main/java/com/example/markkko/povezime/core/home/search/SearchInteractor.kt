package com.example.markkko.povezime.core.home.search


import android.util.Log
import com.example.markkko.povezime.core.data.apis.SearchApi
import com.example.markkko.povezime.core.models.Offer
import com.example.markkko.povezime.core.models.Search
import com.example.markkko.povezime.core.models.User
import com.example.markkko.povezime.core.repositoreis.SearchRepository
import com.example.markkko.povezime.core.repositoreis.UserRepository
import com.example.markkko.povezime.core.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class SearchInteractor @Inject constructor(private val searchApi: SearchApi,
                                           private val userRepository: UserRepository,
                                           private val searchRepository: SearchRepository,
                                           private val schedulerProvider: SchedulerProvider) : ISearchMVP.Interactor {

    override fun getSearchResults(data: Search): Single<List<Offer>> {
        val startTime = System.currentTimeMillis()
        // Pravimo poziv ka serveru
        return searchApi.getSearchResults(data)
                // Govorimo Observable na kojoj niti da izvrši naredbu.
                // Ne sme da bude UI nit jer se vrši poziv preko mreže.
                // U suprotnom bi aplikacija pukla.
                .subscribeOn(schedulerProvider.backgroundThread())
                // Nakon uspešnog poziva smesti Search podatke u
                // repository
                .doOnSuccess { Log.d("response time", ((System.currentTimeMillis() - startTime) / 1000).toString()) }
                .doOnSuccess { Log.d("response num", it.offers.size.toString()) }
                .doOnSuccess {
                    searchRepository.currentSearch = it.search
                }
                // Nakon uspešnog poziva smesti rezultate u
                // repository
                .doOnSuccess { searchRepository.results = it.offers as ArrayList }
                // Mapiraj (pretvori Observable) u listu rezultata
                // da bi ih prosledio dalje u lancu odgovornosti
                .map { it.offers }
    }


    /*override fun getSearchResults(data: Search): Single<List<Offer>> =
            // Pravimo poziv ka serveru
            searchApi.getSearchResults(data)
                    // Govorimo Observable na kojoj niti da izvrši naredbu.
                    // Ne sme da bude UI nit jer se vrši poziv preko mreže.
                    // U suprotnom bi aplikacija pukla.
                    .subscribeOn(schedulerProvider.backgroundThread())
                    // Nakon uspešnog poziva smesti Search podatke u
                    // repository
                    .doOnSuccess {
                        searchRepository.currentSearch = it.search
                    }
                    // Nakon uspešnog poziva smesti rezultate u
                    // repository
                    .doOnSuccess { searchRepository.results = it.offers as ArrayList }
                    // Mapiraj (pretvori Observable) u listu rezultata
                    // da bi ih prosledio dalje u lancu odgovornosti
                    .map { it.offers }*/

    override fun me(): User = userRepository.user
}
