package com.ryben.stackexchange.domain.model

data class User(
    val id: Long,
    val name: String,
    val location: String,
    val reputation: String,
    val dateCreated: String,
)