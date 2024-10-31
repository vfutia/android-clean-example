package com.vfutia.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    ListDataEntity::class
], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun listDataDao(): ListDataDao
}