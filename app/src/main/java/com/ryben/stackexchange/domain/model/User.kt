package com.ryben.stackexchange.domain.model

data class User(
    val id: Long,
    val name: String,
    val location: String,
    val reputation: Long,
    val dateCreated: String, // String for simplicity, but if to be used for logic later, it should be of type Instant
    val profileImageUrl: String = "",
)