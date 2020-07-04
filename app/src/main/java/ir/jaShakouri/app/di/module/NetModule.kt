package ir.jaShakouri.app.di.module

import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import ir.jaShakouri.app.AppClass
import ir.jaShakouri.data.api.ApiInterface
import ir.jaShakouri.domain.AppKeys
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetModule {

    @Provides
    fun providesOkHttpClient(app: AppClass): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckInterceptor(app.baseContext))

        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
            
        .retryOnConnectionFailure(true)
        .build()

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().client(okHttpClient).baseUrl(AppKeys.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(
        ApiInterface::class.java
    )

}