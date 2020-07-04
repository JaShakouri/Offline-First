package ir.jaShakouri.domain.model

import com.google.gson.annotations.SerializedName

class Group {

    @SerializedName("items")
    var items: List<Item>? = null

}