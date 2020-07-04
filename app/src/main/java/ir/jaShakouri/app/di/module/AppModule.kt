package ir.jaShakouri.app.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.jaShakouri.app.AppClass
import ir.jaShakouri.data.local.DataBase
import ir.jaShakouri.data.local.dataBase.dao.ItemDao
import ir.jaShakouri.data.local.sharedPref.Pref
import ir.jaShakouri.domain.AppKeys
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideLocalPref(app: AppClass): Pref {
        return Pref(app)
    }

    @Singleton
    @Provides
    fun provideDatabase(app: AppClass): DataBase = Room.databaseBuilder(
        app,
        DataBase::class.java, AppKeys.DataBaseName
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideItemDao(database: DataBase): ItemDao = database.itemDao()

}