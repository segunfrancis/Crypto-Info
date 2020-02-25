package com.project.segunfrancis.fixaslabchallenge.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.fixaslabchallenge.api.ApiBuilder
import com.project.segunfrancis.fixaslabchallenge.api.ApiService
import com.project.segunfrancis.fixaslabchallenge.database.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.database.CryptoRoomDatabase
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import com.project.segunfrancis.fixaslabchallenge.repository.CryptoRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by SegunFrancis
 */

class CryptoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CryptoRepository
    private val cryptoDao: CryptoDao
    private val apiService: ApiService
    var cryptoList: LiveData<List<ApiResponse>> = MutableLiveData()

    init {
        cryptoDao = CryptoRoomDatabase.getDatabase(application).cryptoDao()
        apiService = ApiBuilder.retrofit.create(ApiService::class.java)
        repository = CryptoRepository(cryptoDao)
        cryptoList = repository.getCryptoList()
    }

    fun insertCryptoList(responseList: List<ApiResponse?>) = viewModelScope.launch {
        repository.insertCryptoList(responseList)
    }
}