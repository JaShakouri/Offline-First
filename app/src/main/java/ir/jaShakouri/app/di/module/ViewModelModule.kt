package ir.jaShakouri.app.di.module

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ir.jaShakouri.app.view.vm.FindViewModel
import ir.jaShakouri.data.usecases.FinderRepository

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    fun providerMainViewModel(repo: FinderRepository): FindViewModel {
        return FindViewModel(repo)
    }

}