package ir.jaShakouri.data.repository.finder

import io.reactivex.Observable
import ir.jaShakouri.data.repository.BaseDataRepository

interface FindRepo<T> : BaseDataRepository<T> {

    fun getData(
        mLocation: String,
        mQuery: String?,
        mOffset: Int
    ): Observable<T>?

    fun loadMore(mOffset: Int): Observable<T>?
    fun clearCache()

}