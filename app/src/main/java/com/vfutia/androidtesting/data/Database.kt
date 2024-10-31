package com.vfutia.androidtesting.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    ListDataEntity::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDataDao(): ListDataDao
}