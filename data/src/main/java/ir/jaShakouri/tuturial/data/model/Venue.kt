package ir.jaShakouri.tuturial.data.model

import com.google.gson.annotations.SerializedName

class Venue {

    @SerializedName("categories")
    var categories: List<Category>? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("location")
    var location: Location? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("photos")
    var photos: Photos? = null

}