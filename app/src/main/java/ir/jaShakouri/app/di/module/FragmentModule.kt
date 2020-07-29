package ir.jaShakouri.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.jaShakouri.app.view.fragment.MainFragment

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun providerMainFragment(): MainFragment

}