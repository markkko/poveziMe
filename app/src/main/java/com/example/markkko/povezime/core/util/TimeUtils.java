package com.example.markkko.povezime.core.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {

  public static DateTime covertTimestampToJodaDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return new DateTime();
    }

    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return formatter.parseDateTime(df.format(timestamp));
  }

  public static String convertJodaDateTimeToString(DateTime dateTime) {
    DateTime date = dateTime;
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    String dtStr = fmt.print(date);
    return dtStr;
  }

  public static DateTime parseJodaDateTime(final String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss");
    return formatter.parseDateTime(dateTimeString);
  }

  public static String convertJodaDateTimeToStringDateOnly(DateTime dateTime) {
    DateTime date = dateTime;
    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
    String dtStr = fmt.print(date);
    return dtStr;
  }

  public static String convertJavaDateToString(final Date date) {
    DateFormat df = new SimpleDateFormat("yy-MM-dd");
    String dtStr = df.format(date);
    return dtStr;
  }

}
