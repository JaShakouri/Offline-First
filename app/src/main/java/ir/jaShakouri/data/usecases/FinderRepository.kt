package ir.jaShakouri.data.usecases

import android.annotation.SuppressLint
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

class FinderRepository @Inject constructor() {

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var itemDao: ItemDao

    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    private var currentDate: String

    init {
        currentDate = sdf.format(Date())
    }

    fun getItems(location: String, query: String, offset: Int): Observable<DataResponse> {

        return Observable.concatArray(
            getItemsFromDb(),
            getItemsFromApi(location, query, offset)
        )

    }

    private fun getItemsFromDb(): Observable<DataResponse> {
        return itemDao.getItems().map {
            return@map DataResponse(it, itemDao.getCount())
        }
    }

    private fun getItemsFromApi(
        location: String,
        query: String,
        offset: Int
    ): Observable<DataResponse> {
        return apiInterface.onLoadVenues(
            AppKeys.client_id, AppKeys.client_secret, currentDate, location, query,
            AppKeys.limit, offset, AppKeys.meters
        ).map {
            return@map DataResponse(
                it.response!!.groups!![0].items!!,
                it.response!!.totalResults!!.toInt()
            )
        }.doOnNext {
            storeUsersInDb(it.list)
        }
    }

    @SuppressLint("CheckResult")
    private fun storeUsersInDb(items: List<Item>) {

        Observable.fromCallable { itemDao.insertAll(items) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.i(this.javaClass.simpleName, "Inserted ${items.size} users from API in DB...")
            }

    }

}