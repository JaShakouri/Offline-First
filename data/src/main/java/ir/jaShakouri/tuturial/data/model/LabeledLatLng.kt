package ir.jaShakouri.tuturial.data.model

import com.google.gson.annotations.SerializedName

class LabeledLatLng {

    @SerializedName("label")
    var label: String? = null

    @SerializedName("lat")
    var lat: Double? = null

    @SerializedName("lng")
    var lng: Double? = null

}