package ir.jaShakouri.app.di.module

import dagger.Module
import dagger.Provides
import ir.jaShakouri.data.usecases.FinderRepository

@Module
class RepositoryModule {

    @Provides
    fun providerFinderRepository() = FinderRepository()

}