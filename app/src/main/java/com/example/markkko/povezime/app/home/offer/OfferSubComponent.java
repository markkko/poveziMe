package com.example.markkko.povezime.app.home.offer;

import com.example.markkko.povezime.app.di.GoogleApiModule;
import com.example.markkko.povezime.app.di.activity.ActivityScope;
import com.example.markkko.povezime.app.home.search.SearchFragment;
import com.example.markkko.povezime.core.home.offer.OfferModule;
import com.example.markkko.povezime.core.home.search.SearchModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {OfferModule.class, GoogleApiModule.class})
public interface OfferSubComponent {
	
	void inject(OfferFragment fragment);

}
