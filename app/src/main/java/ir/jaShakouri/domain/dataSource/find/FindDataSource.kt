package ir.jaShakouri.domain.dataSource.find

import io.reactivex.Observable
import ir.jaShakouri.domain.dataSource.BaseDataSource
import ir.jaShakouri.domain.model.DataResponse

interface FindDataSource : BaseDataSource {

    fun getItems(
        mLocation: String,
        mQuery: String?,
        mOffset: Int
    ): Observable<DataResponse>?

    fun getLoadMore(mOffset: Int): Observable<DataResponse>?
    fun clearCache()

}