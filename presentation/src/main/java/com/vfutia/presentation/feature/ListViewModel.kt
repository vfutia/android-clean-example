package com.vfutia.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vfutia.domain.usecase.FetchList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val fetchList: FetchList
) : ViewModel() {

    private val _state = MutableStateFlow(ListViewState())
    val state = _state.asStateFlow()

    fun fetchList() = viewModelScope.launch(Dispatchers.Main)  {
        _state.update { current -> current.copy(loading = true, hasError = false) }

        try {
            val list = fetchList.invoke()
            delay(2000)
            _state.update { current -> current.copy(list = list) }
        } catch (e: Exception) {
            _state.update { current -> current.copy(hasError = true) }
        } finally {
            _state.update { current -> current.copy(loading = false) }
        }
    }
}