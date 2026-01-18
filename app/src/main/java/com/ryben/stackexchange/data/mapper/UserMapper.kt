package com.ryben.stackexchange.data.mapper

import com.ryben.stackexchange.data.model.UserDto
import com.ryben.stackexchange.domain.model.User
import toFormattedDate

fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        name = this.name.trim(),
        location = this.location ?: "",
        reputation = this.reputation ?: 0,
        dateCreated = this.dateCreated?.toFormattedDate() ?: "",
        profileImageUrl = this.profileImageUrl ?: "",
    )
}


