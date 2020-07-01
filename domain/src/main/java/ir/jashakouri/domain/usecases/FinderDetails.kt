package ir.jashakouri.domain.usecases

import ir.jashakouri.domain.AppKeys
import ir.jashakouri.domain.model.FindResponse
import ir.jashakouri.domain.repo.find.FindRepository
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*

class FinderDetails constructor(private val repository: FindRepository) {

    fun getItems(location: String, query: String, offset: Int): Call<FindResponse>? {

        val sdf = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val currentDate: String = sdf.format(Date())

        return repository.getItems(
            AppKeys.client_id, AppKeys.client_secret, currentDate, location, query,
            AppKeys.limit, offset, AppKeys.meters
        )

    }

}