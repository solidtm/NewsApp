package com.solid.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.solid.newsapp.data.model.APIResponse
import com.solid.newsapp.data.model.util.ResultState
import com.solid.newsapp.domain.usecase.GetNewsHeadlinesUseCase
import com.solid.newsapp.util.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app : Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : AndroidViewModel(app) {
    private val newsHeadLines: MutableLiveData<ResultState<APIResponse.GetTopNewsResponse>> =
        MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(ResultState.Loading())
        try {
            if (isInternetAvailable(app)){
                val apiResult = getNewsHeadlinesUseCase.execute(country, page)
                newsHeadLines.postValue(apiResult)
            }else{
                newsHeadLines.postValue(ResultState.Error("Internet is unavailable"))
            }
        }catch (e: Exception){
            newsHeadLines.postValue(ResultState.Error(e.message.toString()))
        }

    }
}