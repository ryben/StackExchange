package com.ryben.stackexchange.data

import com.ryben.stackexchange.data.model.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("inname") name: String,
        @Query("pagesize") pageSize: Int,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String,
    ): UserResponseDto
}
