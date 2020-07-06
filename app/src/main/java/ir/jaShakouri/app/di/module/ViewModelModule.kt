package ir.jaShakouri.app.di.module

import dagger.Module
import dagger.Provides
import ir.jaShakouri.app.base.vm.BaseViewModel
import ir.jaShakouri.app.view.vm.FindViewModel
import ir.jaShakouri.data.usecases.FinderRepository
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun bindFindViewModel(repo: FinderRepository): BaseViewModel {
        return FindViewModel(repo)
    }

}