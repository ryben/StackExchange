package com.ryben.stackexchange.di

import com.ryben.stackexchange.data.UserRepositoryImpl
import com.ryben.stackexchange.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
     fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository


}