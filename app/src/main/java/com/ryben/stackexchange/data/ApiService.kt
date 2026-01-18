package com.ryben.stackexchange.data

import com.ryben.stackexchange.data.model.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(
        @Query("inname") name: String,
        @Query("pagesize") pageSize: String,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String,
    ): Result<UserResponseDto>
}
