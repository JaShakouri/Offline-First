package ir.jashakouri.presentation.repositories

import ir.jashakouri.domain.model.FindResponse
import ir.jashakouri.domain.repo.find.FindRepository
import ir.jashakouri.presentation.api.ApiInterface
import retrofit2.Call

class FinderCloudDataSource constructor(private val apiInterface: ApiInterface) : FindRepository {

    override fun getItems(
        client_id: String,
        client_secret: String,
        mDateTime: String,
        mLocation: String,
        mQuery: String?,
        mLimit: Int,
        mOffset: Int,
        mMeter: Int
    ): Call<FindResponse>? {
        return apiInterface.onLoadVenues(
            client_id,
            client_secret,
            mDateTime,
            mLocation,
            mQuery,
            mLimit,
            mOffset,
            mMeter
        )
    }
}