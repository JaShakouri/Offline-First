package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName
import ir.jashakouri.domain.model.Category

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