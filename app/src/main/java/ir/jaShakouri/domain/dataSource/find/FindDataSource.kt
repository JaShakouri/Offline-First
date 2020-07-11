package ir.jaShakouri.domain.dataSource.find

import io.reactivex.Observable
import ir.jaShakouri.domain.dataSource.BaseDataSource
import ir.jaShakouri.domain.model.DataResponse

interface FindDataSource : BaseDataSource {

    fun items(
        mLocation: String,
        mQuery: String?,
        mOffset: Int
    ): Observable<DataResponse>?

    fun loadMore(mOffset: Int): Observable<DataResponse>?
    fun clearCache()

}