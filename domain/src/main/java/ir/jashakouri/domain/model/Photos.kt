package ir.jashakouri.domain.model

import com.google.gson.annotations.SerializedName

class Photos {

    @SerializedName("count")
    var count: Long? = null

    @SerializedName("groups")
    var groups: List<Group>? = null

}