package ir.jaShakouri.data.local.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ir.jaShakouri.domain.AppKeys
import ir.jaShakouri.domain.model.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM ${AppKeys.Item_TableName}")
    fun getItems(): Single<List<Item>>

    @Query("SELECT COUNT(*) FROM ${AppKeys.Item_TableName}")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Item>)

    @Query("DELETE FROM ${AppKeys.Item_TableName}")
    fun deleteAll()

}