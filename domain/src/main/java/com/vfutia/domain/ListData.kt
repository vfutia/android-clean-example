package com.vfutia.domain

import java.util.Date

data class ListData(
    val name: String,
    val description: String,
    val created: Date = Date()
)