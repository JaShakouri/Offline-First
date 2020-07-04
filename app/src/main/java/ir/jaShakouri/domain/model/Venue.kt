package ir.jaShakouri.domain.model

import com.google.gson.annotations.SerializedName

class Venue {

    @SerializedName("location")
    var location: Location? = null

    @SerializedName("name")
    var name: String = ""

}