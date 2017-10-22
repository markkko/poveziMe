package com.example.markkko.povezime.app.notifications;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class Notifications {
  public static final String ArraySize = "array_size";

  public static final String ArrayElement = "array_";

  public static final String NewOrderReceived = "new_order";

  public static void store(final Context context, final String action) {

    SharedPreferences preferences = context.getSharedPreferences("preferences", context.MODE_PRIVATE);
    int arraySize = preferences.getInt(Notifications.ArraySize, 0);

    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(Notifications.ArrayElement + arraySize, action);
    editor.putInt(Notifications.ArraySize, (arraySize + 1));
    editor.commit();
  }

  public static void clear(final Context context) {
    SharedPreferences preferences = context.getSharedPreferences("preferences", context.MODE_PRIVATE);
    int arraySize = preferences.getInt(Notifications.ArraySize, 0);

    SharedPreferences.Editor editor = preferences.edit();
    for (int i = 0; i < arraySize; i++) {
      try {
        editor.remove(ArrayElement + i);
      } catch (Exception e) {
      }
    }
    editor.putInt(ArraySize, 0);
    editor.commit();
  }

  public static List<String> getAll(final Context context) {
    List<String> all = new ArrayList<>();

    SharedPreferences preferences = context.getSharedPreferences("preferences", context.MODE_PRIVATE);
    int arraySize = preferences.getInt(Notifications.ArraySize, 0);

    for (int i = 0; i < arraySize; i++) {
      try {
        String value = preferences.getString(ArrayElement + i, null);
        if (value != null) {
          all.add(value);
        }
      } catch (Exception e) {
      }
    }

    return all;
  }
}
