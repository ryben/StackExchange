package com.ryben.stackexchange.data

import com.ryben.stackexchange.domain.UserRepository
import com.ryben.stackexchange.domain.model.User

class UserRepositoryImpl : UserRepository {
    override fun searchUserByName(name: String): Result<List<User>> {
        TODO("Not yet implemented")
    }
}