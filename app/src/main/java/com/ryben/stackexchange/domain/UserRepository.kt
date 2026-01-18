package com.ryben.stackexchange.domain

import com.ryben.stackexchange.domain.model.User

interface UserRepository {
    suspend fun searchUserByName(name: String): Result<List<User>>
}