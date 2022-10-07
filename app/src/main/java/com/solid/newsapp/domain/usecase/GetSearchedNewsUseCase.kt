package com.solid.newsapp.domain.usecase

import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.data.model.util.ResultState
import com.solid.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchedQuery: String): ResultState<APIResponse.GetTopNewsResponse> {
        return newsRepository.getSearchedNews(searchedQuery)
    }
}