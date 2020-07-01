package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("reasonName")
    var reasonName: String? = null

    @SerializedName("reasons")
    var reasons: Reasons? = null

    @SerializedName("referralId")
    var referralId: String? = null

    @SerializedName("summary")
    var summary: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("venue")
    var venue: Venue? = null

}