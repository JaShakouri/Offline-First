package ir.jaShakouri.data.local.dataBase.dao

import io.reactivex.Observable
import ir.jaShakouri.domain.model.Item


interface ItemDao {

    //    @Query("SELECT * FROM ${AppKeys.Item_TableName}")
    fun getItems(): Observable<List<Item>>

    //    @Query("SELECT COUNT(*) FROM ${AppKeys.Item_TableName}")
    fun getCount(): Int

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<Item>)

}