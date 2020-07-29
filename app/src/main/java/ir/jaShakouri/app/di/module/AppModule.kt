package ir.jaShakouri.app.di.module

import dagger.Module
import dagger.Provides
import ir.jaShakouri.app.AppClass
import ir.jaShakouri.data.local.sharedPref.Pref
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providePref(appClass: AppClass): Pref {
        return Pref(appClass)
    }


}