package ir.jaShakouri.domain.dataSource.find

import io.reactivex.Observable
import ir.jaShakouri.domain.model.FindResponse
import ir.jaShakouri.domain.dataSource.BaseDataSource

interface FindDataSource : BaseDataSource {
    fun getItems(client_id: String,
                 client_secret: String,
                 mDateTime: String,
                 mLocation: String,
                 mQuery: String?,
                 mLimit: Int,
                 mOffset: Int,
                 mMeter: Int): Observable<FindResponse>?
}