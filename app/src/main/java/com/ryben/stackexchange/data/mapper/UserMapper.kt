package com.ryben.stackexchange.data.mapper

import com.ryben.stackexchange.data.model.UserDto
import com.ryben.stackexchange.domain.model.User

fun UserDto.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        location = this.location ?: "",
        reputation = this.reputation ?: "",
        dateCreated = this.dateCreated ?: "",
    )
}


