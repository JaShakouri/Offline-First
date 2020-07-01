package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName
import ir.jashakouri.domain.model.Filter

class SuggestedFilters {

    @SerializedName("filters")
    var filters: List<Filter>? = null

    @SerializedName("header")
    var header: String? = null

}