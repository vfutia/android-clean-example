package com.vfutia.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "listdata")
internal data class ListDataEntity (
    @PrimaryKey val id: Long,
    val name: String,
    val description: String
)