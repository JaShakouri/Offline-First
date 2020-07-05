package ir.jaShakouri.data.local.dataBase.converter

import com.google.gson.Gson
import ir.jaShakouri.domain.model.Venue

object VenueConverter {

    @JvmStatic
    fun toString(venue: Venue): String {
        return Gson().toJson(venue)
    }

    @JvmStatic
    fun toVenue(string: String): Venue {
        return Gson().fromJson(string, Venue::class.java)
    }

}