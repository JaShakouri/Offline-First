package ir.jashakouri.domain.repo.find

import ir.jashakouri.domain.model.FindResponse
import ir.jashakouri.domain.repo.BaseRepository
import retrofit2.Call

interface FindRepository : BaseRepository {
    fun getItems(location: String, query: String, offset: Int): Call<FindResponse>
}