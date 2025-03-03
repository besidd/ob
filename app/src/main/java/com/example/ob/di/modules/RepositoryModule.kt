package com.example.ob.di.modules

import com.example.ob.repo.CardRepository
import com.example.ob.repo.CardRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesCardRepository(): CardRepository = CardRepositoryImpl()

}