package ir.jaShakouri.app.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ir.jaShakouri.app.AppClass
import ir.jaShakouri.app.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        NetModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<AppClass> {

    @Component.Builder
    interface Builder {

        fun application(@BindsInstance app: AppClass): Builder

        fun build(): AppComponent
    }

}