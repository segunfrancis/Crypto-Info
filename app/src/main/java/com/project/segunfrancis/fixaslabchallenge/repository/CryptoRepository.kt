package com.project.segunfrancis.fixaslabchallenge.repository

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.api.ApiService
import com.project.segunfrancis.fixaslabchallenge.database.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import retrofit2.Call

/**
 * Created by SegunFrancis
 */

class CryptoRepository() {
    internal lateinit var apiService: ApiService
    internal lateinit var cryptoDao: CryptoDao

    constructor(apiService: ApiService) : this() {
        this.apiService = apiService
    }

    constructor(cryptoDao: CryptoDao) : this() {
        this.cryptoDao = cryptoDao
    }

    fun getCryptoCoins(): Call<List<ApiResponse>> = apiService.getCryptoCoins()

    fun getCryptoList(): LiveData<List<ApiResponse>> = cryptoDao.getCryptoList()

    suspend fun insertCryptoList(responseList: List<ApiResponse?>) = cryptoDao.insertCryptoList(responseList)
}