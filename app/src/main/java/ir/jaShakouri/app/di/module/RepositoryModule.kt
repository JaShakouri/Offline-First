package ir.jaShakouri.app.di.module

import dagger.Module
import dagger.Provides
import ir.jaShakouri.data.api.ApiInterface
import ir.jaShakouri.data.local.dataBase.dao.ItemDao
import ir.jaShakouri.data.repository.finder.FinderRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFindRepository(apiInterface: ApiInterface, itemDao: ItemDao): FinderRepository {
        return FinderRepository(
            apiInterface,
            itemDao
        )
    }

}