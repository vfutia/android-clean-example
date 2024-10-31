package com.vfutia.presentation.feature

import com.vfutia.domain.ListData

data class ListViewState(
    val loading: Boolean = false,
    val hasError: Boolean = false,
    val list: List<ListData> = listOf()
)