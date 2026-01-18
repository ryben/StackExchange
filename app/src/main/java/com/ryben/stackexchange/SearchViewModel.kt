package com.ryben.stackexchange

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class UiState(
    val searchText: String
)

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState(""))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onSearchTextUpdated(newSearch: String) {
        _uiState.update { it.copy(searchText = newSearch) }
    }

    fun getSearchResultsStub(): List<String> {
        return listOf(
            "Michael Scott",
            "Jim Halpert",
            "Dwight Schrute"
        )
    }
}