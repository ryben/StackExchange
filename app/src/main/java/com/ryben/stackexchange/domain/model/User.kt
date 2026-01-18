package com.ryben.stackexchange.domain.model

data class User(
    val id: Long,
    val name: String,
    val location: String,
    val reputation: String,
    val dateCreated: String, // String for simplicity, but if to be used for logic later, it should be of type Instant
)