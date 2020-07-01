package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class FindResponse {

    @SerializedName("meta")
    var meta: Meta? = null

    @SerializedName("response")
    var response: Response? = null

}