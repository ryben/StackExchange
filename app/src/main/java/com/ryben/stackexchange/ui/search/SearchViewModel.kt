package com.ryben.stackexchange.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryben.stackexchange.domain.UserRepository
import com.ryben.stackexchange.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class SearchUiState(
    val searchText: String = "",
    val searchResults: List<User> = emptyList(),
    val status: SearchStatus = SearchStatus.IDLE,
)

enum class SearchStatus {
    IDLE, LOADING, SUCCESS, ERROR
}

@HiltViewModel
class SearchViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    fun onSearchTextUpdated(newSearch: String) {
        _searchUiState.update { it.copy(searchText = newSearch) }
    }

    fun onSearch(searchText: String) {
        // Ignore if empty search
        if (searchText.isBlank()) {
            return
        }

        viewModelScope.launch {
            _searchUiState.update { it.copy(status = SearchStatus.LOADING) }

            userRepository.searchUserByName(searchText)
                .onSuccess { result ->
                    _searchUiState.update {
                        it.copy(
                            status = SearchStatus.SUCCESS,
                            searchResults = result
                        )
                    }
                }
                .onFailure {
                    _searchUiState.update { it.copy(status = SearchStatus.ERROR) }
                    // TODO: Show error in UI
                }
        }
    }

}