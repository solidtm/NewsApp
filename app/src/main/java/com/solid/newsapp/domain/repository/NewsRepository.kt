package com.solid.newsapp.domain.repository

import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.data.model.util.ResultState
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadLines(country: String, page: Int) : ResultState<APIResponse.GetTopNewsResponse>
    suspend fun getSearchedNews(searchQuery: String): ResultState<APIResponse.GetTopNewsResponse>

    //database functions
    suspend fun saveNews(article: APIResponse.Article)
    suspend fun deleteNews(article: APIResponse.Article)
    fun getSavedNews(): Flow<List<APIResponse.Article>>   //return livedata cos we want to get real time updates, but not best practice
}