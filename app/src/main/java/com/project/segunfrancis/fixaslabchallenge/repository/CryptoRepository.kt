package com.project.segunfrancis.fixaslabchallenge.repository

import com.project.segunfrancis.fixaslabchallenge.api.ApiService
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import retrofit2.Call

/**
 * Created by SegunFrancis
 */

class CryptoRepository(var apiService: ApiService) {

    fun getCryptoCoins(): Call<List<ApiResponse>> = apiService.getCryptoCoins()
}