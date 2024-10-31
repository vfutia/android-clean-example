package com.vfutia.data

import com.vfutia.domain.DataSource
import com.vfutia.domain.ListData
import com.vfutia.domain.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListRepositoryImpl(
    private val dataSource: DataSource
) : ListRepository {
    override suspend fun fetchList(): List<ListData> {
        return withContext(Dispatchers.IO) {
            dataSource.fetchListData().ifEmpty {
                listOf(
                    ListData(
                        name = "test",
                        description = "just a test",
                    ),
                    ListData(
                        name = "test2",
                        description = "just a test",
                    ),
                    ListData(
                        name = "test3",
                        description = "just a test",
                    ),
                    ListData(
                        name = "test4",
                        description = "just a test",
                    ),
                )
            }
        }
    }
}