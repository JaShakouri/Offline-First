package ir.jaShakouri.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ir.jaShakouri.app.di.viewModel.ViewModelInjector
import ir.jaShakouri.app.di.viewModel.ViewModelKey
import ir.jaShakouri.app.view.vm.FindViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FindViewModel::class)
    internal abstract fun bindFindViewModel(findViewModel: FindViewModel): ViewModel

    @Binds
    internal abstract fun provideViewModelInjector(injector: ViewModelInjector): ViewModelProvider.Factory

}