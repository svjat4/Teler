package com.kampus.teler.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kampus.teler.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    companion object{
        private const val TAG = "SearchViewModel"
    }
    private val searchList = MutableLiveData<ArticleResponse>()
    val resultSearchDataArticle: LiveData<ArticleResponse> = searchList

    private val _isLoadingProgress = MutableLiveData<Boolean>()
    val isLoadingProgress: LiveData<Boolean> = _isLoadingProgress

    fun searchDataArticle (queryAlergi: String){
        _isLoadingProgress.value = true
        val client = ApiConfig.getApiService().searchArticle(queryAlergi)
        client.enqueue(object: Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                _isLoadingProgress.value = false
                if (response.isSuccessful) {
                    searchList.value = response.body()
                } else {
                    Log.e(SearchViewModel.TAG, "onFailure: ${response.message()}, Error occurred while fetching data")
                }
            }
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isLoadingProgress.value = false
                Log.e(SearchViewModel.TAG, "onFailure: ${t.message}")
            }
        } )
    }
}