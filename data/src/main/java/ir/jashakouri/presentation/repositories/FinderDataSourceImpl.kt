package ir.jashakouri.presentation.repositories

import ir.jashakouri.domain.model.FindResponse
import ir.jashakouri.domain.repo.find.FindRepository
import retrofit2.Call

class FinderDataSourceImpl constructor(private val repository: FindRepository) : FindRepository {

    override fun getItems(
        client_id: String,
        client_secret: String,
        mDateTime: String,
        mLocation: String,
        mQuery: String?,
        mLimit: Int,
        mOffset: Int,
        mMeter: Int
    ): Call<FindResponse> {
        return repository.getItems(
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