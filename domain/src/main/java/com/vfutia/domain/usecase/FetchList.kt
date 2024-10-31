package com.vfutia.domain.usecase

import com.vfutia.domain.ListData
import com.vfutia.domain.ListRepository
import javax.inject.Inject

class FetchList @Inject constructor(
    private val repository: ListRepository
) {
    suspend fun invoke(): List<ListData> {
        return repository.fetchList()
    }
}