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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryben.stackexchange.domain.model.User


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
fun UserInfoRoute(onBack: () -> Unit) {
    UserInfoScreen(
        user = User(
            id = 100,
            name = "Rey Benedicto",
            location = "Cavite",
            reputation = "500",
            dateCreated = "20260118"
        ),
        onBack = onBack
    )
}

@Composable
fun UserInfoScreen(
    user: User,
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
                    modifier = Modifier.size(100.dp)
                )

                Text(text = user.name)
                Text(text = user.location)
                Text(text = user.reputation)
                Text(text = "Badges")
                Text(text = user.dateCreated)
            }
        }

    }
}