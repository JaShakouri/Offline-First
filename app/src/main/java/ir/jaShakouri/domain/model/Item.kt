package ir.jaShakouri.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ir.jaShakouri.domain.AppKeys

@Entity(tableName = AppKeys.Item_TableName)
class Item {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

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