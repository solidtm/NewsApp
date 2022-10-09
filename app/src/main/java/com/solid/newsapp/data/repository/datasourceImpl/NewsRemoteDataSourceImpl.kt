package com.solid.newsapp.data.repository.datasourceImpl

import com.solid.newsapp.data.api.NewsApiService
import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadLines(country: String, page: Int): Response<APIResponse.GetTopNewsResponse> {
        return newsApiService.getTopHeadLines(country, page)
    }
}