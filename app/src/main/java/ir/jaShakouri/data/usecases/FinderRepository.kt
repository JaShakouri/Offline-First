package ir.jaShakouri.data.usecases

import io.reactivex.Observable
import ir.jaShakouri.data.api.ApiContainer
import ir.jaShakouri.data.api.ApiInterface
import ir.jaShakouri.domain.AppKeys
import ir.jaShakouri.domain.model.DataResponse
import ir.jaShakouri.domain.model.FindResponse
import java.text.SimpleDateFormat
import java.util.*

class FinderRepository {

    var apiInterface: ApiInterface

    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    private var currentDate: String

    init {
        currentDate = sdf.format(Date())
        apiInterface = ApiContainer.getInstance()
    }

    fun getItems(location: String, query: String, offset: Int): Observable<DataResponse> {

        return Observable.concatArray(
            getItemsFromApi(location, query, offset)
        )

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
        }
    }

}