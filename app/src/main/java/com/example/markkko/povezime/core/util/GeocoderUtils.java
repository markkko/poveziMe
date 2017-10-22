package com.example.markkko.povezime.core.util;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class GeocoderUtils {

  Context context;

  @Inject
  public GeocoderUtils (Context context) {
    this.context = context;
  }


  public String getCityName(LatLng latLng) {
    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
    List<Address> addresses = null;
    try {
      addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (addresses != null) {
      return addresses.get(0).getAddressLine(0);
      //String stateName = addresses.get(0).getAddressLine(1);
      //String countryName = addresses.get(0).getAddressLine(2);
    }
    return null;
  }
}
