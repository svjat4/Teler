package com.kampus.teler.retrofit

import com.kampus.teler.model.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("search/article")
    fun searchArticle(@Query("q") alergi: String): Call<ArticleResponse>
}