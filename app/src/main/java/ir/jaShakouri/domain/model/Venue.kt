package ir.jaShakouri.domain.model

import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import ir.jaShakouri.data.local.dataBase.converter.LocationConverter

class Venue {

    @TypeConverters(LocationConverter::class)
    @SerializedName("location")
    var location: Location? = null

    @SerializedName("name")
    var name: String = ""

}