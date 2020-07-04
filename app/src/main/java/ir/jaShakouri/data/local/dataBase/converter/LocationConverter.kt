package ir.jaShakouri.data.local.dataBase.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import ir.jaShakouri.domain.model.Location

object LocationConverter {

    @TypeConverter
    @JvmStatic
    fun toString(location: Location): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    @JvmStatic
    fun toLocation(string: String): Location {
        return Gson().fromJson(string, Location::class.java)
    }

}