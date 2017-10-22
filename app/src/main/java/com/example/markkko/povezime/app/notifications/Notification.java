package com.example.markkko.povezime.app.notifications;

import android.content.Context;

public abstract class Notification {

  public abstract String getAction();

  public abstract String generateTitle(Context context);

  public abstract String generateMessage(Context context);
}
