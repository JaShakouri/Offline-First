package ir.jaShakouri.domain.model

import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("groups")
    var groups: List<Group>? = null

    @SerializedName("totalResults")
    var totalResults: Long? = null

}