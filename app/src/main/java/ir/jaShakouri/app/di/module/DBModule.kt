package ir.jaShakouri.app.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.jaShakouri.app.AppClass
import ir.jaShakouri.data.local.dataBase.DataBase
import ir.jaShakouri.data.local.dataBase.dao.ItemDao
import ir.jaShakouri.domain.AppKeys
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDB(appClass: AppClass): DataBase = Room.databaseBuilder(
        appClass.applicationContext,
        DataBase::class.java,
        AppKeys.DataBaseName
    ).allowMainThreadQueries().build()

    @Provides
    fun provideItemDao(dataBase: DataBase): ItemDao = dataBase.itemDao()

}