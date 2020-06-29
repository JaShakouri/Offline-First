package ir.jaShakouri.tuturial.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

}