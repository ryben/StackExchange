package com.ryben.stackexchange.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.ryben.stackexchange.R
import com.ryben.stackexchange.ui.theme.CustomColor

@Preview
@Composable
fun SearchResultItemPreview() {
    SearchResultItem(
        "Rey Benedicto",
        "Cavite",
        "100",
        onClick = {},
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SearchResultItem(
    name: String,
    location: String,
    reputation: String,
    profileImageUrl: String = "",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val roundedCornerVal = 24.dp

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(roundedCornerVal))
            .clickable { onClick() }
            .border(
                width = 0.5.dp,
                color = Color.LightGray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(roundedCornerVal)
            )
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        if (LocalInspectionMode.current) {
            Image(
                painter = painterResource(R.drawable.default_user_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        } else {
            GlideImage(
                model = profileImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
            ) { requestBuilder ->
                requestBuilder.placeholder(R.drawable.default_user_icon)
            }
        }

        Column(
            modifier = modifier
                .weight(1F)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(
                space = 4.dp
            ),
        ) {
            Text(
                text = name.trim(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            if (location.trim().isNotBlank()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(text = location, color = Color.DarkGray)
                }
            }
        }


        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = reputation,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = CustomColor.Orange
            )
            Text(
                text = "REP",
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
        }


    }
}