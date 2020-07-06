package ir.jaShakouri.data.usecases

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ir.jaShakouri.data.api.ApiInterface
import ir.jaShakouri.data.local.dataBase.dao.ItemDao
import ir.jaShakouri.domain.AppKeys
import ir.jaShakouri.domain.model.DataResponse
import ir.jaShakouri.domain.model.Item
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FinderRepository @Inject constructor(var apiInterface: ApiInterface, var itemDao: ItemDao) {

    private val tag = "FinderRepository"

    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    private var currentDate: String

    var location = ""
    var query = ""
    var pageOffset = 1

    init {
        currentDate = sdf.format(Date())
    }

    fun getItems(location: String, query: String, offset: Int): Observable<DataResponse> {

        this.location = location
        this.query = query
        this.pageOffset = offset

        return Observable.concatArray(
            getItemFromDB(), getItemsFromApi()
        )

    }

    fun getMoreItems(offset: Int): Observable<DataResponse> {
        this.pageOffset = offset
        return getItemsFromApi()
    }

    private fun getItemFromDB(): Observable<DataResponse> {
        return itemDao.getItems().map {
            return@map DataResponse(it, it.size, false)
        }.toObservable().doOnNext {
            Log.i(tag, "getItemFromDB: ${it.list.size}")
        }
    }

    private fun getItemsFromApi(): Observable<DataResponse> {
        return apiInterface.onLoadVenues(
            AppKeys.client_id, AppKeys.client_secret,
            this.currentDate, this.location, this.query,
            AppKeys.limit, this.pageOffset, AppKeys.meters
        ).map {
            return@map DataResponse(
                it.response!!.groups!![0].items!!,
                it.response!!.totalResults!!.toInt(), true
            )
        }.doOnNext {
            storeListItem(it.list)
        }
    }

    private fun storeListItem(list: List<Item>) {
        Observable.fromCallable { itemDao.insertAll(list) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }

}