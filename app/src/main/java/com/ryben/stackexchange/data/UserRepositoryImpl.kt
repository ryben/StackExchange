package com.ryben.stackexchange.data

import com.ryben.stackexchange.data.mapper.toDomain
import com.ryben.stackexchange.di.IoDispatcher
import com.ryben.stackexchange.domain.UserRepository
import com.ryben.stackexchange.domain.model.User
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val apiService: ApiService
) : UserRepository {
    override suspend fun searchUserByName(name: String): Result<List<User>> {
        return withContext(ioDispatcher) {
            try {
                val dto = apiService.getUsers(
                    name = name,
                    pageSize = PAGE_SIZE,
                    order = ORDER_ASC,
                    sort = SORT_BY_NAME,
                    site = SITE_STACKOVERFLOW,
                )

                Result.success(dto.items.map { it.toDomain() })
            } catch (e: CancellationException) {
                throw e // Let the parent scope handle it the cancellation i.e.
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


    companion object { // Defaults
        const val PAGE_SIZE = 20
        const val ORDER_ASC = "asc"
        const val SORT_BY_NAME = "name"
        const val SITE_STACKOVERFLOW = "stackoverflow"
    }
}