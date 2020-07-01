package ir.jashakouri.presentation.api

import ir.jashakouri.domain.model.FindResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("explore")
    fun onLoadVenues(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("v") mDateTime: String,
        @Query("ll") mLocation: String,
        @Query("query") mQuery: String?,
        @Query("limit") mLimit: Int,
        @Query("offset") mOffset: Int,
        @Query("radius") mMeter: Int
    ): Call<FindResponse>

}