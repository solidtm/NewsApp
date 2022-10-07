package com.solid.newsapp.domain.usecase

import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    fun execute(): Flow<List<APIResponse.Article>> {
        return newsRepository.getSavedNews()
    }
}