package com.project.segunfrancis.fixaslabchallenge.api

import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by SegunFrancis
 */
interface ApiService {

    @GET("ticker/?limit=50")
    fun getCryptoCoins(): Call<List<ApiResponse>>
}