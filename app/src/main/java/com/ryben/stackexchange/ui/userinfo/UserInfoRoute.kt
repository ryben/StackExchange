package com.ryben.stackexchange.ui.userinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ryben.stackexchange.domain.model.User
import com.ryben.stackexchange.ui.search.SearchViewModel


@Preview
@Composable
fun UserInfoRoutePreview() {
    UserInfoScreen(
        user = User(
            id = 100,
            name = "Rey Benedicto",
            location = "Cavite",
            reputation = "500",
            dateCreated = "20260118"
        ),
        onBack = {}
    )
}

@Composable
fun UserInfoRoute(onBack: () -> Unit, viewModel: SearchViewModel) {
    val uiState by viewModel.searchUiState.collectAsStateWithLifecycle()

    UserInfoScreen(
        user = uiState.selectedUser,
        onBack = onBack
    )
}

@Composable
fun UserInfoScreen(
    user: User?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = com.ryben.stackexchange.R.drawable.default_user_icon),
                    contentDescription = "",
                    modifier = Modifier.size(120.dp)
                )

                user?.let {
                    Text(text = it.name)
                    Text(text = it.location)
                    Text(text = it.reputation)
                    Text(text = "Badges")
                    Text(text = it.dateCreated)
                }

            }
        }

    }
}