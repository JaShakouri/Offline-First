package ir.jaShakouri.domain.model

import com.google.gson.annotations.SerializedName

class Location {

    @SerializedName("city")
    var city: String = ""

    @SerializedName("country")
    var country: String = ""

    @SerializedName("lat")
    var lat: Double = 0.0

    @SerializedName("lng")
    var lng: Double = 0.0

}