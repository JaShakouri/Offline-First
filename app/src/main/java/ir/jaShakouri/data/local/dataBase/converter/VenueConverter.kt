package ir.jaShakouri.data.local.dataBase.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import ir.jaShakouri.domain.model.Venue

object VenueConverter {

    @TypeConverter
    @JvmStatic
    fun toString(venue: Venue): String {
        return Gson().toJson(venue)
    }

    @TypeConverter
    @JvmStatic
    fun toVenue(string: String): Venue {
        return Gson().fromJson(string, Venue::class.java)
    }

}