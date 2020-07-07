package ir.jaShakouri.app.di.component

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
        NetModule::class,
        ActivityModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        DBModule::class
    ]
)
interface AppComponent : AndroidInjector<AppClass> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance appClass: AppClass): AppComponent
    }

}