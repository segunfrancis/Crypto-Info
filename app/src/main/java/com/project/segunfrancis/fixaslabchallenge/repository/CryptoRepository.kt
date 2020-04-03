package com.project.segunfrancis.fixaslabchallenge.repository

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.ApiService
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import retrofit2.Call

/**
 * Created by SegunFrancis
 */

class CryptoRepository() {
    private lateinit var apiService: ApiService
    private lateinit var cryptoDao: CryptoDao

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