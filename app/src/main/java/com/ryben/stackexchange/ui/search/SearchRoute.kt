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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ryben.stackexchange.R
import com.ryben.stackexchange.domain.model.User
import com.ryben.stackexchange.ui.SearchStatus
import com.ryben.stackexchange.ui.SearchUiState
import com.ryben.stackexchange.ui.SearchViewModel


@Preview
@Composable
fun SearchScreenPreview() {
    val uiState = SearchUiState(
        searchText = "",
        searchResults = listOf(
            User(0, "Michael Scott", "Scranton", "5", "Jan 1, 2010"),
            User(1, "Jim Halpert", "Scranton", "10", "Feb 2, 2011"),
            User(2, "Dwight Schrute", "Scranton", "900", "Mar 2, 2012"),
        ),
        status = SearchStatus.SUCCESS
    )

    SearchScreen(
        uiState = uiState,
        onSearchTextChanged = {},
        onSearch = {},
        onSelectUser = {},
    )
}


@Composable
fun SearchRoute(navToUserInfoRoute: () -> Unit, viewModel: SearchViewModel) {
    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = uiState,
        onSearchTextChanged = viewModel::onSearchTextUpdated,
        onSearch = viewModel::onSearch,
        onSelectUser = { user ->
            viewModel.selectUser(user)
            navToUserInfoRoute()
        }
    )
}


@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onSearchTextChanged: (newSearchText: String) -> Unit,
    onSearch: (searchText: String) -> Unit,
    onSelectUser: (user: User) -> Unit,
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
                        contentDescription = null,
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
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 120.dp),
                contentAlignment = Alignment.Center
            ) {
                when (uiState.status) {

                    SearchStatus.IDLE -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_list_24),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = Color.Gray),
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "RESULTS SHOW HERE",
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                            )
                        }
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
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(items = uiState.searchResults, key = { it.id }) { item ->
                                    SearchResultItem(
                                        name = item.name,
                                        location = item.location,
                                        reputation = item.reputation,
                                        profileImageUrl = item.profileImageUrl,
                                        onClick = { onSelectUser(item) }
                                    )
                                }
                            }
                        } else {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.outline_list_24),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(color = Color.Gray),
                                    modifier = Modifier.size(40.dp)
                                )
                                Text(
                                    text = "NO MATCHES",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center,
                                    fontSize = 16.sp,
                                )
                            }
                        }
                    }

                    SearchStatus.ERROR -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_error_24),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = Color.Gray),
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                text = "An error occurred.\nPlease try again later.",
                                color = Color.Gray,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }


            }
        }
    }
}