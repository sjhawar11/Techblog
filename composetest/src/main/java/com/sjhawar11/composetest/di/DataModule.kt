package com.sjhawar11.composetest.di

import com.sjhawar11.composetest.data.FeedRepository
import com.sjhawar11.composetest.data.FeedRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Available everywhere in the app
abstract class DataModule {

  @Binds
  @Singleton
  abstract fun bindFeedRepository(
    feedRepository: FeedRepositoryImpl
  ): FeedRepository
}