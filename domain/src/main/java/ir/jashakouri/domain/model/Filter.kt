package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class Filter {

    @SerializedName("key")
    var key: String? = null

    @SerializedName("name")
    var name: String? = null

}