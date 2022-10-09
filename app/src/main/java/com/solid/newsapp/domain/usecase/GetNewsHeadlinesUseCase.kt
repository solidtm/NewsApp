package com.solid.newsapp.domain.usecase

import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.data.model.util.ResultState
import com.solid.newsapp.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): ResultState<APIResponse.GetTopNewsResponse>{
        return newsRepository.getNewsHeadLines(country, page)
    }
}