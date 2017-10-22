package com.example.markkko.povezime.app.util;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean isNullOrEmpty(final String string) {
		return string == null || "".equals(string);
	}

	public static boolean isNullOrEmpty(final CharSequence string) {
		return string == null || "".equals(string);
	}

	public static boolean isNullOrEmpty(final TextView input) {
		return input == null || input.getText().toString().equals("");
	}

	public static boolean isNullOrEmpty(final EditText input) {
		return input == null || input.getText().toString().equals("");
	}

	public static boolean isNumberBad(final EditText input) {
		if (input == null) {
			return true;
		}

		try {
			float number = Float.parseFloat(input.getText().toString());
			return number < 0;
		} catch (Exception e) {
			return true;
		}
	}

	public static float getFloatSafe(final EditText input) {
		try {
			return Float.parseFloat(input.getText().toString());
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getStringSafe(final TextView input) {
		if (input == null || input.getText() == null) return "";
		return getStringSafe(input.getText().toString());
	}

	public static String getStringSafe(final EditText input) {
		if (input == null || input.getText() == null) return "";
		return getStringSafe(input.getText().toString());
	}

	public static String getStringSafe(final String input) {
		if (isNullOrEmpty(input)) {
			return "";
		}
		return input;
	}

	public static String getStringSafe(final String input, final String replacement) {
		if (isNullOrEmpty(input)) {
			return replacement;
		}
		return input;
	}

	public static boolean isValidEmail(EditText target) {
		return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target.getText()).matches();
	}

	public static boolean isValidEmail(CharSequence target) {
		return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
	}

	public static boolean isValidPhoneNumber(CharSequence phoneNumber) {
		return !TextUtils.isEmpty(phoneNumber) && android.util.Patterns.PHONE.matcher(phoneNumber).matches();
	}

	public static boolean isValidWebsite(EditText target) {
		return !isNullOrEmpty(target) && Patterns.WEB_URL.matcher(target.getText()).matches();
	}

	public static boolean isValidWebsite(CharSequence target) {
		return !TextUtils.isEmpty(target) && Patterns.WEB_URL.matcher(target).matches();
	}

	public static boolean passwordCharValidation(String password) {
		if (password.length() < 8) {
			return false;
		}
		String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.]).*$";
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		return !password.matches(".*\\d.*") || !matcher.matches();
	}

	public static boolean passwordValidationShort(String password) {
		return password.length() > 8;
	}
}
