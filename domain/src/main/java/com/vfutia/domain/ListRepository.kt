package com.vfutia.domain

interface ListRepository {
    suspend fun fetchList(): List<ListData>
}
