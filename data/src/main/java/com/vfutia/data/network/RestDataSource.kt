package com.vfutia.data.network

import com.vfutia.domain.DataSource
import com.vfutia.domain.ListData
import retrofit2.http.GET

interface RestDataSource : DataSource {

    @GET("/hiring.json")
    override fun fetchListData(): List<ListData>
}