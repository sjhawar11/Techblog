package com.sjhawar11.composetest.di

import com.sjhawar11.composetest.data.FeedRepository
import com.sjhawar11.composetest.fakes.FakeFeedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class] // You need to create a DataModule in production code first
)
object TestModule {

    @Singleton
    @Provides
    fun provideFakeRepo(): FakeFeedRepository = FakeFeedRepository()

    @Provides
    fun provideRepo(fake: FakeFeedRepository): FeedRepository = fake
}