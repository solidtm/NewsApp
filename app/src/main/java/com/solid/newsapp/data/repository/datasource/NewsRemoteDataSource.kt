package com.solid.newsapp.data.repository.datasource

import com.solid.newsapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadLines(country: String, page: Int): Response<APIResponse.GetTopNewsResponse>

}