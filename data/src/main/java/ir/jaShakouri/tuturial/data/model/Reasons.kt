package ir.jaShakouri.tuturial.data.model

import com.google.gson.annotations.SerializedName

class Reasons {

    @SerializedName("count")
    var count: Long? = null

    @SerializedName("items")
    var items: List<Item>? = null

}