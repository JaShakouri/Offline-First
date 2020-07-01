package ir.jaShakouri.tuturial

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class AppClass : Application() {

    companion object {
        lateinit var Instance: AppClass
    }

    override fun onCreate() {
        super.onCreate()
        Instance = this
        Fresco.initialize(this)
    }

}