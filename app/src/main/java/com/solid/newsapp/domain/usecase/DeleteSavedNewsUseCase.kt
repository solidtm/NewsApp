package com.solid.newsapp.domain.usecase

import com.solid.newsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val repository: NewsRepository) {
}