package com.example.markkko.povezime.app.di


import android.content.Context
import com.example.markkko.povezime.core.home.PlaceAutocompleteAdapter
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.AutocompleteFilter
import com.google.android.gms.location.places.GeoDataClient
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import dagger.Module
import dagger.Provides

@Module
class GoogleApiModule {

    @Provides
    fun provideAutocompleteAdapter(context: Context, geoDataClient: GeoDataClient,
                                   bounds: LatLngBounds, filter: AutocompleteFilter): PlaceAutocompleteAdapter {
        return PlaceAutocompleteAdapter(context, geoDataClient, bounds,
                filter)
    }

    @Provides
    fun provideAutocompleteFilter(): AutocompleteFilter {
        return AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build()
    }

    @Provides
    fun provideGeoDataClient(context: Context): GeoDataClient = Places.getGeoDataClient(context, null)

    /*@Provides
            //@ActivityScope
    fun provideGoogleApiClient(context: Context): GoogleApiClient {
        return GoogleApiClient.Builder(context)
                .addApi(Places.GEO_DATA_API)
                .addApi(LocationServices.API)
                .build()
    }*/

    @Provides
            //@ActivityScope
    fun provideBoundsGreater(): LatLngBounds {
        return LatLngBounds(
                LatLng(-34.041458, 150.790100), LatLng(-33.682247, 151.383362))
    }

}
