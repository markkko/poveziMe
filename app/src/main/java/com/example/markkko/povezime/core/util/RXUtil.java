package com.example.markkko.povezime.core.util;

import io.reactivex.disposables.CompositeDisposable;


public class RXUtil {

	public static void initDisposables(CompositeDisposable disposables) {
		if (disposables == null || disposables.isDisposed()) {
			disposables = new CompositeDisposable();
		}
	}

	public static void dispose(CompositeDisposable disposables) {
		if (disposables != null && !disposables.isDisposed()) {
			disposables.dispose();
		}
	}
}
