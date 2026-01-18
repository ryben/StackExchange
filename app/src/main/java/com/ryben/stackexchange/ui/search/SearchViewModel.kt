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
import timber.log.Timber
import javax.inject.Inject


data class UiState(
    val searchText: String,
    val searchResults: List<User>,
)

@HiltViewModel
class SearchViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(UiState("", emptyList()))
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onSearchTextUpdated(newSearch: String) {
        _uiState.update { it.copy(searchText = newSearch) }
    }

    fun onSearch(searchText: String) {
        // TODO: Specify dispatcher/context
        viewModelScope.launch {
            userRepository.searchUserByName(searchText)
                .onSuccess { result ->
                    _uiState.update { it.copy(searchResults = result) }
                }
                .onFailure {
                    Timber.e("Error on search: $searchText")
                    // TODO: Show error in UI
                }
        }
    }

}