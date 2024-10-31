package com.vfutia.data

import com.vfutia.domain.DataSource
import com.vfutia.domain.ListData
import com.vfutia.domain.ListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ListRepositoryImpl (
    private val persistence: DataSource,
    private val network: DataSource
) : ListRepository {
    override suspend fun fetchList(): List<ListData> {
        return withContext(Dispatchers.IO) {
            try {
                //I know this is going to fail, just want to make sure the
                //plumbing for the network stuff works
                network.fetchListData()
            } catch (e: Exception) {
                persistence.fetchListData().ifEmpty {
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
}