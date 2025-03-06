package com.example.ob.di.modules

import com.example.ob.utils.ObApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppProviders {

    @Provides
    @Singleton
    fun providesApplication(): ObApplication = ObApplication()
}