package com.ryben.stackexchange.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ryben.stackexchange.domain.model.User


@Preview
@Composable
fun SearchScreenPreview() {
    var searchText by mutableStateOf("")

    SearchScreen(
        searchText = searchText,
        onSearchTextChanged = {},
        onSearch = {},
        searchResults = listOf(
            User("0", "Michael Scott", "Scranton", "", ""),
            User("1", "Jim Halpert", "Scranton", "", ""),
            User("2", "Dwight Schrute", "Scranton", "", ""),
        )
    )
}


@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        searchText = uiState.searchText,
        onSearchTextChanged = viewModel::onSearchTextUpdated,
        onSearch = viewModel::onSearch,
        searchResults = uiState.searchResults
    )
}


@Composable
fun SearchScreen(
    searchText: String,
    onSearchTextChanged: (newSearchText: String) -> Unit,
    onSearch: (searchText: String) -> Unit,
    searchResults: List<User>
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
                    value = searchText,
                    onValueChange = { onSearchTextChanged(it) },
                    singleLine = true,
                )
                Button(onClick = { onSearch(searchText) }) {
                    Text("Search")
                }
            }

            Text(text = "Search results")

            // Search Results List
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = searchResults) { item ->
                    Text(text = item.name, fontSize = 18.sp)
                }
            }
        }
    }
}