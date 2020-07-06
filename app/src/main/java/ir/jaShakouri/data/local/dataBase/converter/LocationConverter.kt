package ir.jaShakouri.data.local.dataBase.converter

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import ir.jaShakouri.domain.model.Location

object LocationConverter {

    @JvmStatic
    @TypeConverter
    fun toString(location: Location): String {
        return Gson().toJson(location)
    }

    @JvmStatic
    @TypeConverter
    fun toLocation(string: String): Location {
        return Gson().fromJson(string, Location::class.java)
    }

}