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
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun providesOkHttpsClient(appClass: AppClass):
            OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(ChuckInterceptor(appClass.applicationContext))
        .retryOnConnectionFailure(true)

        //for create clean authenticator 401 refresh token accessToken ...
        //.authenticator()

        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(AppKeys.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

}