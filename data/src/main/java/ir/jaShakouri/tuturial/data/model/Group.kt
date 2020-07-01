package ir.jaShakouri.tuturial.data.model

import com.google.gson.annotations.SerializedName

class Group {

    @SerializedName("items")
    var items: List<Item>? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("type")
    var type: String? = null

}