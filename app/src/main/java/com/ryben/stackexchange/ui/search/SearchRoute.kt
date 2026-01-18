package com.ryben.stackexchange.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ryben.stackexchange.R
import com.ryben.stackexchange.domain.model.User


@Preview
@Composable
fun SearchScreenPreview() {
    val uiState = SearchUiState(
        searchText = "",
        searchResults = listOf(
            User("0", "Michael Scott", "Scranton", "5", ""),
            User("1", "Jim Halpert", "Scranton", "10", ""),
            User("2", "Dwight Schrute", "Scranton", "900", ""),
        ),
        status = SearchStatus.SUCCESS
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
                .padding(16.dp)
        ) {

            // Search bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchTextField(
                    uiState.searchText,
                    onSearchTextChanged,
                    onSearch,
                    modifier = Modifier.weight(1F),
                    placeholder = "Search by name"
                )
                IconButton(onClick = { onSearch(uiState.searchText) }) {
                    Image(
                        painter = painterResource(id = R.drawable.outline_search_24),
                        contentDescription = "",
                    )
                }
            }

            Text(
                text = "SEARCH RESULTS",
                modifier = Modifier.padding(top = 20.dp),
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp),
                contentAlignment = Alignment.Center
            ) {
                when (uiState.status) {

                    SearchStatus.IDLE -> {
                        Text(text = "ENTER A NAME TO START SEARCH", color = Color.Gray)
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
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(items = uiState.searchResults, key = { it.id }) { item ->
                                    SearchResultItem(
                                        name = item.name,
                                        location = item.location,
                                        reputation = item.reputation
                                    )
                                }
                            }
                        } else {
                            Text(text = "NO MATCHES", color = Color.Gray)

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