package com.example.markkko.povezime.app.util;

public class NumberUtils {

  public static float clean(Float number) {
    if (number == null) return 0;
    return number;
  }

  public static int clean(Integer number) {
    if (number == null) return 0;
    return number;
  }

  public static long clean(Long number) {
    if (number == null) return 0L;
    return number;
  }

  public static double clean(Double number) {
    if (number == null) return 0;
    return number;
  }
}
