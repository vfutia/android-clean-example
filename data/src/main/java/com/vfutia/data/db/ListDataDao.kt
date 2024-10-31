package com.vfutia.data.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ListDataDao {
    @Query("SELECT * FROM listdata")
    fun fetchListData(): List<ListDataEntity>
}