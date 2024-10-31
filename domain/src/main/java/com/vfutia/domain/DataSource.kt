package com.vfutia.domain

interface DataSource {
    fun fetchListData(): List<ListData>
}