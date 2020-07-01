package ir.jashakouri.domain.repo.find

import ir.jashakouri.domain.model.FindResponse
import ir.jashakouri.domain.repo.BaseRepository
import retrofit2.Call

interface FindRepository : BaseRepository {
    fun getItems(client_id: String,
                 client_secret: String,
                 mDateTime: String,
                 mLocation: String,
                 mQuery: String?,
                 mLimit: Int,
                 mOffset: Int,
                 mMeter: Int): Call<FindResponse>?
}