package ir.jaShakouri.tuturial.data.model

import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("groups")
    var groups: List<Group>? = null

    @SerializedName("headerFullLocation")
    var headerFullLocation: String? = null

    @SerializedName("headerLocation")
    var headerLocation: String? = null

    @SerializedName("headerLocationGranularity")
    var headerLocationGranularity: String? = null

    @SerializedName("suggestedBounds")
    var suggestedBounds: SuggestedBounds? = null

    @SerializedName("suggestedFilters")
    var suggestedFilters: SuggestedFilters? = null

    @SerializedName("totalResults")
    var totalResults: Long? = null

}