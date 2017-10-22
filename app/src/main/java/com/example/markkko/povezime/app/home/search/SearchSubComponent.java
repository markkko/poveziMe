package com.example.markkko.povezime.app.home.search;

import com.example.markkko.povezime.app.di.GoogleApiModule;
import com.example.markkko.povezime.app.di.activity.ActivityScope;
import com.example.markkko.povezime.core.home.search.SearchModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {SearchModule.class, GoogleApiModule.class})
public interface SearchSubComponent {
	
	void inject(SearchFragment fragment);

}
