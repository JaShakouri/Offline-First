package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("code")
    var code: Long? = null

    @SerializedName("requestId")
    var requestId: String? = null

}