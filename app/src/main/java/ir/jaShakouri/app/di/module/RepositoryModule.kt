package ir.jaShakouri.app.di.module

import dagger.Binds
import dagger.Module
import ir.jaShakouri.data.repository.finder.FindRepo
import ir.jaShakouri.domain.model.DataResponse

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideFindRepository(finder : FindRepo<DataResponse>): FindRepo<DataResponse>

}