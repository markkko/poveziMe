package com.example.markkko.povezime.app.util;


public class AppConstants {

	//Dagger
	public static final String ACTIVITY_CONTEXT = "activity_context";
	public static final String APPLICATION_CONTEXT = "application_context";


	public static final String FROM_NAVIGATION = "from_navigation";

	public static final int NETWORK_CONNECTION_TIMEOUT = 60;

	//cache
	public static final long CACHE_SIZE = 60;
	public static final int CACHE_MAX_AGE = 60;
	public static final int CACHE_MAX_STALE = 60;
	public static final int API_RETRY_COUNT = 60;

	// broadcast receiver intent filters
	public static final String REGISTRATION_COMPLETE = "registrationComplete";
	public static final String PUSH_NOTIFICATION = "pushNotification";

	// id to handle the notification in the notification tray
	public static final int NOTIFICATION_ID = 100;
	public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

	public static final String SHARED_PREF_FIREBASE = "ah_firebase";
	public static final String PREF_USER = "shared_pref_user";
	public static final String PREF_REG_ID = "regId";
}
