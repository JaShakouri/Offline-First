package ir.jaShakouri.tuturial.data.model

import com.google.gson.annotations.SerializedName

class SuggestedFilters {

    @SerializedName("filters")
    var filters: List<Filter>? = null

    @SerializedName("header")
    var header: String? = null

}