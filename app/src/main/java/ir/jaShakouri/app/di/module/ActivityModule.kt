package ir.jaShakouri.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.jaShakouri.app.view.activity.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun providerMainActivity(): MainActivity

}