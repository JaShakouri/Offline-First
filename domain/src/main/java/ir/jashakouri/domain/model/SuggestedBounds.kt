package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class SuggestedBounds {

    @SerializedName("ne")
    var ne: Ne? = null

    @SerializedName("sw")
    var sw: Sw? = null

}