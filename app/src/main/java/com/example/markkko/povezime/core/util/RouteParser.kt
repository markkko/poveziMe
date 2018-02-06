package com.example.markkko.povezime.core.util

import com.google.android.gms.maps.model.LatLng
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class RouteParser {

    /**
     * Receives a JSONObject and returns a list of lists containing latitude and longitude
     */
    fun parse(jObject: JSONObject): List<HashMap<String, String>>? {

        try {
            // Izvlačimo objekat "routes" iz odgovora.
            // Interesuje nas samo prva ruta, ne i alternative
            val jRoute = jObject.getJSONArray("routes").get(0)

            // Nakon toga izvlačimo "legs" iz odgovora.
            // Broj nogara zavisi od broja putnih tačaka.
            // Ako je broj putnih tačaka jedan nula, imaćemo samo jednu nogaru.
            val jLegs = (jRoute as JSONObject).getJSONArray("legs") ?: return null

            // Inicijalizujemo trasu
            val path = ArrayList<HashMap<String, String>>()

            // Prolazimo kroz sve nogare.
            for (j in 0 until jLegs.length()) {

                // Dohvatamo broj koraka u svakoj nogari.
                // Jedan korak je jednak jednoj ulici.
                // Sledeći korak se nastavlja nakon skreanja
                val jSteps = (jLegs.get(j) as JSONObject).getJSONArray("steps")

                // Prolazimo kroz sve korake
                for (k in 0 until jSteps.length()) {
                    // Svaki korak sadrži "polyline" koji sadrži koordinate koje su enkodovane.
                    // Mi te koordinate moramo da dekodiramo.
                    // Dobijamo listu koordinata
                    val polyline = ((jSteps.get(k) as JSONObject).get("polyline") as JSONObject).get("points") as String
                    val list = decodePoly(polyline) // as List<LatLng>

                    // Prolazimo kroz listu koordinata
                    for (l in list.indices) {

                        val lat = java.lang.Double.toString(list[l].latitude)
                        val lng = java.lang.Double.toString(list[l].longitude)

                        val hm = HashMap<String, String>()
                        hm.put("lat", lat)
                        hm.put("lng", lng)

                        //Dodajemo svaku koordinatu u trasu
                        path.add(hm)
                    }
                }
            }
            return path

        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: Exception) {
        }

        return null
    }

    /**
     * Method to decode polyline points
     * Courtesy : http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     */
    private fun decodePoly(encoded: String): List<LatLng> {

        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(lat.toDouble() / 1E5,
                    lng.toDouble() / 1E5)
            poly.add(p)
        }

        return poly
    }
}