package com.solid.newsapp.domain.usecase

import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.data.model.util.ResultState
import com.solid.newsapp.domain.repository.NewsRepository

class SavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: APIResponse.Article) = newsRepository.saveNews(article)
}