package com.ryben.stackexchange.data

import com.ryben.stackexchange.data.mapper.toDomain
import com.ryben.stackexchange.data.model.UserDto
import com.ryben.stackexchange.domain.model.User
import org.junit.Assert
import org.junit.Test


class UserMapperTest {
    // Sample mapping test
    @Test
    fun `UserDto toDomain() maps to User domain model`() {
        // Given
        val userDto = UserDto(
            id = 10,
            name = "Rey Benedicto",
            location = "Cavite",
            reputation = "20",
            dateCreated = 1453075200
        )

        val expectedUser = User(
            id = 10,
            name = "Rey Benedicto",
            location = "Cavite",
            reputation = "20",
            dateCreated = "Jan 18, 2016"
        )

        // Then
        Assert.assertEquals(userDto.toDomain(), expectedUser)
    }

}