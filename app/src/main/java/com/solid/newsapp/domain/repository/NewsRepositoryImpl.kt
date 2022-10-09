package com.solid.newsapp.domain.repository

import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.data.model.util.ResultState
import com.solid.newsapp.data.repository.datasource.NewsRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsHeadLines(country: String, page: Int): ResultState<APIResponse.GetTopNewsResponse> {
        return responseToResultState(newsRemoteDataSource.getTopHeadLines(country, page))
    }

    //function to convert a Response object to it's different result states.
    private fun responseToResultState(response: Response<APIResponse.GetTopNewsResponse>): ResultState<APIResponse.GetTopNewsResponse>{
        if (response.isSuccessful){
            response.body()?.let {result ->
                return ResultState.Success(result)
            }
        }

        return ResultState.Error(response.message())
    }

    override suspend fun getSearchedNews(searchQuery: String): ResultState<APIResponse.GetTopNewsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: APIResponse.Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: APIResponse.Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<APIResponse.Article>> {
        TODO("Not yet implemented")
    }
}