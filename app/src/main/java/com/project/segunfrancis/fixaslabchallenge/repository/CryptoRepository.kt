package com.project.segunfrancis.fixaslabchallenge.repository

import com.project.segunfrancis.fixaslabchallenge.api.ApiBuilder
import com.project.segunfrancis.fixaslabchallenge.api.ApiService
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by SegunFrancis
 */

class CryptoRepository(var apiService: ApiService) {
/*
    init {
        apiService = ApiBuilder.retrofit.create(ApiService::class.java)
    }*/

    fun getCryptoCoins(): Call<List<ApiResponse>> = apiService.getCryptoCoins()
}