package com.example.markkko.povezime.app.di.app;


import android.content.Context;

import com.example.markkko.povezime.BuildConfig;
import com.example.markkko.povezime.app.util.AppConstants;
import com.example.markkko.povezime.app.util.AppSchedulerProvider;
import com.example.markkko.povezime.core.util.Constants;
import com.example.markkko.povezime.core.util.SchedulerProvider;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

@Singleton
@Module
public class ApplicationModule {
	@Provides
	@Singleton
	@Named("isDebug")
	boolean provideIsDebug() {
		return BuildConfig.DEBUG;
	}

	@Provides
	@Singleton
	@Named("networkTimeoutInSeconds")
	int provideNetworkTimeoutInSeconds() {
		return AppConstants.NETWORK_CONNECTION_TIMEOUT;
	}

	@Provides
	@Singleton
	HttpUrl provideEndpoint() {
		return HttpUrl.parse(Constants.BASE_URL);
	}

	@Provides
	@Singleton
	SchedulerProvider provideAppScheduler() {
		return new AppSchedulerProvider();
	}

	@Provides
	@Singleton
	@Named("cacheSize")
	long provideCacheSize() {
		return AppConstants.CACHE_SIZE;
	}

	@Provides
	@Singleton
	@Named("cacheMaxAge")
	int provideCacheMaxAgeMinutes() {
		return AppConstants.CACHE_MAX_AGE;
	}

	@Provides
	@Singleton
	@Named("cacheMaxStale")
	int provideCacheMaxStaleDays() {
		return AppConstants.CACHE_MAX_STALE;
	}

	@Provides
	@Singleton
	@Named("retryCount")
	public int provideApiRetryCount() {
		return AppConstants.API_RETRY_COUNT;
	}

	@Provides
	@Singleton
	@Named("cacheDir")
	File provideCacheDir(Context context) {
		return context.getCacheDir();
	}

	@Provides
	@Named("isConnect")
	boolean provideIsConnect(Context context) {
		//return Utils.isConnected(context);
		return true;
	}

	@Provides
	FirebaseAnalytics provideFirebaseAnalytics(Context context) {
		return FirebaseAnalytics.getInstance(context);
	}


}
