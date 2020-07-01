package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class Location {

    @SerializedName("address")
    var address: String? = null

    @SerializedName("cc")
    var cc: String? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("country")
    var country: String? = null

    @SerializedName("crossStreet")
    var crossStreet: String? = null

    @SerializedName("distance")
    var distance: Long? = null

    @SerializedName("formattedAddress")
    var formattedAddress: List<String>? = null

    @SerializedName("labeledLatLngs")
    var labeledLatLngs: List<LabeledLatLng>? = null

    @SerializedName("lat")
    var lat: Double? = null

    @SerializedName("lng")
    var lng: Double? = null

    @SerializedName("state")
    var state: String? = null

}