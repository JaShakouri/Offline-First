package ir.jaShakouri.app

import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ir.jaShakouri.app.di.component.DaggerAppComponent
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

        DaggerAppComponent.factory().create(this).inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }

}