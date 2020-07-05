package ir.jaShakouri.data.api

import com.readystatesoftware.chuck.ChuckInterceptor
import ir.jaShakouri.app.AppClass
import ir.jaShakouri.domain.AppKeys
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiContainer {

    companion object {

        private var okHttpClient: OkHttpClient? = null
        private var retrofit: Retrofit? = null
        private var apiInterface: ApiInterface? = null

        private fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()

            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

            .addInterceptor(ChuckInterceptor(AppClass.Instance))

            .retryOnConnectionFailure(true)
            .build()

        private fun retrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder().client(okHttpClient).baseUrl(AppKeys.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        private fun apiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(
            ApiInterface::class.java
        )

        fun getInstance(): ApiInterface {

            if (okHttpClient == null)
                okHttpClient =
                    okHttpClient()

            if (retrofit == null)
                retrofit =
                    retrofit(
                        okHttpClient!!
                    )

            if (apiInterface == null)
                apiInterface =
                    apiInterface(
                        retrofit!!
                    )

            return apiInterface!!

        }

    }

}
