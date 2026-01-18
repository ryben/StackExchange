package com.ryben.stackexchange.data.model

import com.google.gson.annotations.SerializedName

data class UserResponseDto(
    @SerializedName("items")
    val items: List<UserDto>
)