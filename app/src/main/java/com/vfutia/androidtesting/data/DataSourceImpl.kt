package com.vfutia.androidtesting.data

import com.vfutia.domain.DataSource
import com.vfutia.domain.ListData
import javax.inject.Inject

class DataSourceImpl @Inject constructor (
    private val listDataDao: ListDataDao
) : DataSource {
    override fun fetchListData(): List<ListData> = listDataDao.fetchListData().map { data -> data.toListData() }

    private fun ListDataEntity.toListData(): ListData {
        return ListData(name, description)
    }
}