package ir.jaShakouri.tuturial.data.remote.repo.find

import ir.jaShakouri.tuturial.app.AppKeys
import ir.jaShakouri.tuturial.data.model.FindResponse
import ir.jaShakouri.tuturial.data.remote.api.ApiContainer
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*

class FindRepository {

    fun getItems(
        location: String,
        query: String,
        offset: Int
    ): Call<FindResponse> {

        val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val currentDate: String = sdf.format(Date())

        return ApiContainer.getInstance().onLoadVenues(
            AppKeys.client_id,
            AppKeys.client_secret,
            currentDate, location, query,
            AppKeys.limit, offset, AppKeys.meters
        )

    }
}