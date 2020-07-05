package ir.jaShakouri.app

import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco

class AppClass : MultiDexApplication() {

    companion object {
        lateinit var Instance: AppClass
    }

    override fun onCreate() {
        super.onCreate()
        Instance = this

        Fresco.initialize(this)

    }

}