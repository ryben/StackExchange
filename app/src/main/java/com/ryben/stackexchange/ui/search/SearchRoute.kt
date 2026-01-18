package com.ryben.stackexchange.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ryben.stackexchange.domain.model.User


@Preview
@Composable
fun SearchScreenPreview() {
    val uiState = SearchUiState(
        searchResults = listOf(
            User("0", "Michael Scott", "Scranton", "", ""),
            User("1", "Jim Halpert", "Scranton", "", ""),
            User("2", "Dwight Schrute", "Scranton", "", ""),
        ),
    )

    SearchScreen(
        uiState = uiState,
        onSearchTextChanged = {},
        onSearch = {},
    )
}


@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState,
        onSearchTextChanged = viewModel::onSearchTextUpdated,
        onSearch = viewModel::onSearch,
    )
}


@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onSearchTextChanged: (newSearchText: String) -> Unit,
    onSearch: (searchText: String) -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            // Search bar
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = uiState.searchText,
                    onValueChange = { onSearchTextChanged(it) },
                    singleLine = true,
                )
                Button(onClick = { onSearch(uiState.searchText) }) {
                    Text("Search")
                }
            }

            Text(text = "Search results")

            Box {
                when (uiState.status) {

                    SearchStatus.IDLE -> {
                        Text(text = "Results will show here")
                    }

                    SearchStatus.LOADING -> {
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }

                    SearchStatus.SUCCESS -> {
                        if (uiState.searchResults.isNotEmpty()) {
                            // Search Results List
                            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                items(items = uiState.searchResults, key = { it.id }) { item ->
                                    Text(text = item.name, fontSize = 18.sp)
                                }
                            }
                        } else {
                            Text(text = "No matches")

                        }
                    }

                    SearchStatus.ERROR -> {
                        // TODO: Show error message
                    }
                }


            }
        }
    }
}