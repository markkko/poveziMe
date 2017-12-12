package com.example.markkko.povezime

import com.example.markkko.povezime.core.home.search.SearchInteractor
import com.example.markkko.povezime.core.models.SearchResultsReq
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import javax.inject.Inject


class SearchInteractorTest {


    @Inject
    lateinit var searchInteractor: SearchInteractor


    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this@SearchInteractorTest)
    }

    @Test
    fun getSearchResults_shouldReturnListOfOffers() {


        //Mockito.`when`(searchApi.getSearchResults(Mockito.any())).thenReturn()


        val request = SearchResultsReq.getDefaultRequest(0, "2017-11-11")

        Assert.assertNotNull(searchInteractor.getSearchResults(request))

    }

}