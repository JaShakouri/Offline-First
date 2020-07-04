package ir.jaShakouri.app

import androidx.databinding.DataBindingUtil
import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ir.jaShakouri.app.dataBinding.appBinding.AppDataBindingComponent
import ir.jaShakouri.app.di.DaggerAppComponent
import javax.inject.Inject


class AppClass : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {
        lateinit var Instance: AppClass
    }

    override fun onCreate() {
        super.onCreate()
        Instance = this

        Fresco.initialize(this)

        DataBindingUtil.setDefaultComponent(AppDataBindingComponent())

        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }

}