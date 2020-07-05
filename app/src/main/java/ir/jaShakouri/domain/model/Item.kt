package ir.jaShakouri.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Item : Serializable {


    @SerializedName("reasonName")
    var reasonName: String? = null

    @SerializedName("referralId")
    var referralId: String? = null

    @SerializedName("summary")
    var summary: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("venue")
    var venue: Venue? = null

}