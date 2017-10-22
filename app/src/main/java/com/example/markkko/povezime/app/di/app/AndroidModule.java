package com.example.markkko.povezime.app.di.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.markkko.povezime.app.PoveziMeApplication;
import com.example.markkko.povezime.app.util.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Singleton
@Module
public class AndroidModule {

	private PoveziMeApplication application;

	public AndroidModule(PoveziMeApplication application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Context provideContext() {
		return application.getApplicationContext();
	}

	@Provides
	@Singleton
	Resources provideResources() {
		return application.getResources();
	}

	@Singleton
	@Provides
	SharedPreferences provideSharedPreferences() {
		return application.getSharedPreferences(AppConstants.SHARED_PREF_USER, Context.MODE_PRIVATE);
	}

}
