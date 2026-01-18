package com.ryben.stackexchange.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryben.stackexchange.R

@Preview
@Composable
fun SearchResultItemPreview() {
    SearchResultItem(
        "Rey Benedicto",
        "Cavite",
        "100",
        imageResourceId = R.drawable.default_user_icon
    )
}

@Composable
fun SearchResultItem(
    name: String,
    location: String,
    reputation: String,
    imageResourceId: Int = R.drawable.default_user_icon,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(24.dp))
            .padding(horizontal = 32.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )

        Column(modifier = modifier.weight(1F), Arrangement.spacedBy(4.dp)) {
            Text(
                text = name.trim(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            if (location.trim().isNotBlank()) {
                Text(text = location, color = Color.DarkGray)
            }
        }


        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = reputation,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6214)
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