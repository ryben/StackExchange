package com.ryben.stackexchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.ryben.stackexchange.ui.theme.StackExchangeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackExchangeTheme {
                SearchRoute()
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    var searchText by mutableStateOf("")

    SearchScreen(
        searchText = searchText,
        onSearchTextChanged = { searchText = it },
        searchResults = listOf(
            "Michael Scott",
            "Jim Halpert",
            "Dwight Schrute"
        )
    )
}


@Composable
fun SearchRoute(viewModel: SearchViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        searchText = uiState.searchText,
        onSearchTextChanged = viewModel::onSearchTextUpdated,
        searchResults = viewModel.getSearchResultsStub()
    )
}


@Composable
fun SearchScreen(
    searchText: String,
    onSearchTextChanged: (newSearchText: String) -> Unit,
    searchResults: List<String>
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            // Search bar
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(value = searchText, onValueChange = { onSearchTextChanged(it) })
                Button(onClick = {}) {
                    Text("Search")
                }
            }

            Text(text = "Search results")

            // Search Results List
            LazyColumn {
                items(items = searchResults) { item ->
                    Text(text = item, fontSize = 18.sp)
                }
            }
        }
    }
}