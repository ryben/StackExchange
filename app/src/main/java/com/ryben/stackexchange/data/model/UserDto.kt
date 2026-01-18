package com.ryben.stackexchange.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("user_id")
    val id: String,

    @SerializedName("display_name")
    val name: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("reputation")
    val reputation: String,

    @SerializedName("creation_date")
    val dateCreated: String,
)