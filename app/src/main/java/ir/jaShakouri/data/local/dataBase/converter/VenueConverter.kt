package ir.jaShakouri.data.local.dataBase.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import ir.jaShakouri.domain.model.Venue

object VenueConverter {

    @JvmStatic
    @TypeConverter
    fun toString(venue: Venue): String {
        return Gson().toJson(venue)
    }

    @JvmStatic
    @TypeConverter
    fun toVenue(string: String): Venue {
        return Gson().fromJson(string, Venue::class.java)
    }

}