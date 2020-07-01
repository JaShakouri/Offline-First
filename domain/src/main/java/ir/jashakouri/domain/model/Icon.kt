package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class Icon {

    @SerializedName("prefix")
    var prefix: String? = null

    @SerializedName("suffix")
    var suffix: String? = null

}