package com.ryben.stackexchange.ui.userinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ryben.stackexchange.R
import com.ryben.stackexchange.domain.model.User
import com.ryben.stackexchange.ui.SearchViewModel
import com.ryben.stackexchange.ui.theme.CustomColor


@Preview
@Composable
fun UserInfoRoutePreview() {
    UserInfoScreen(
        user = User(
            id = 100,
            name = "Rey Benedicto",
            location = "Cavite",
            reputation = "500",
            dateCreated = "Jul 1, 2022",
            profileImageUrl = "",
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserInfoScreen(
    user: User?,
    onBack: () -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(8.dp)
        ) {

            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                user?.let {
                    // Profile Image
                    if (LocalInspectionMode.current) {
                        Image(
                            painter = painterResource(R.drawable.default_user_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        GlideImage(
                            model = user.profileImageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .shadow(
                                    elevation = 24.dp,
                                    shape = CircleShape,
                                    clip = false
                                )
                                .clip(CircleShape),
                        ) { requestBuilder ->
                            requestBuilder.placeholder(R.drawable.default_user_icon)
                        }
                    }


                    // Name
                    Text(
                        text = it.name,
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                    )

                    // Location
                    Text(
                        text = it.location,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Reputation
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier

                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(alpha = 0.18f),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(vertical = 24.dp)

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.diamond),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(CustomColor.Orange),
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(top = 4.dp)
                            )
                            Text(
                                text = it.reputation,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Text(
                            text = stringResource(R.string.reputation),
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    // Reputation
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.Gray.copy(alpha = 0.18f),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(vertical = 24.dp)

                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(Color.Gray),
                                modifier = Modifier
                                    .size(36.dp)
                                    .padding(top = 4.dp)
                            )
                            Text(
                                text = it.dateCreated,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Text(
                            text = stringResource(R.string.joined_date),
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}