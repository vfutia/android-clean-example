package com.vfutia.data.db

import com.vfutia.domain.DataSource
import com.vfutia.domain.ListData
import javax.inject.Inject

internal class RoomDataSource @Inject constructor (
    private val listDataDao: ListDataDao
) : DataSource {
    override fun fetchListData(): List<ListData> = listDataDao.fetchListData().map { data -> data.toListData() }

    private fun ListDataEntity.toListData(): ListData {
        return ListData(name, description)
    }
}