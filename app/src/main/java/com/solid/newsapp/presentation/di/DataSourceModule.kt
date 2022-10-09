package com.solid.newsapp.presentation.di

import com.solid.newsapp.data.api.NewsApiService
import com.solid.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.solid.newsapp.data.repository.datasourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsApiService)
    }
}