package ir.jaShakouri.tuturial.utils

class Utility {

    companion object {

        fun latLangConverter(
            latitude: Double,
            longitude: Double,
            height: Int,
            width: Int,
            zoom: Int
        ): String? {
            return "https://api.mapbox.com/v4/mapbox.emerald/pin-m-heart+e6353b(" + longitude + "," + latitude + ")/" + longitude + "," + latitude + "," + zoom + "/" + width + "x" + height + ".png?" + "access_token=" + "pk.eyJ1IjoiaXJobnZpc2l0IiwiYSI6ImNqcnB6YTVjZDEyOG80OXBzb2JwZHY1ZTIifQ.HacauZ6ID0KQOGkksgNs1w"
        }

    }

}