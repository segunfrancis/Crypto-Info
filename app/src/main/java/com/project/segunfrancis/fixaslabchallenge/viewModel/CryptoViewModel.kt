package com.project.segunfrancis.fixaslabchallenge.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.ApiBuilder
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.ApiService
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoRoomDatabase
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.Local
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.Remote
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse
import com.project.segunfrancis.fixaslabchallenge.repository.CryptoRepository
import com.project.segunfrancis.fixaslabchallenge.useCases.GetLocalData
import com.project.segunfrancis.fixaslabchallenge.useCases.GetRemoteData
import com.project.segunfrancis.fixaslabchallenge.useCases.SetLocalData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by SegunFrancis
 */

class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    private val cryptoViewModel = Job()

    //private val repository: CryptoRepository
    private val cryptoDao: CryptoDao
    private val apiService: ApiService
    private val dataRepo: DataRepository
    private val localData: GetLocalData
    private val remoteData: GetRemoteData
    private val setLocal: SetLocalData
    //var cryptoList: LiveData<List<ApiResponse>> = MutableLiveData()

    init {
        cryptoDao = CryptoRoomDatabase.getDatabase(application).cryptoDao()
        apiService = ApiBuilder.retrofit.create(
            ApiService::class.java)
//        repository = CryptoRepository(cryptoDao)
//        cryptoList = repository.getCryptoList()

        dataRepo = DataRepository(Local(cryptoDao), Remote(apiService))
        localData = GetLocalData(dataRepo)
        remoteData = GetRemoteData(dataRepo)
        setLocal = SetLocalData(dataRepo)
    }

/*    fun insertCryptoList(responseList: List<ApiResponse?>) = viewModelScope.launch {
        repository.insertCryptoList(responseList)
    }*/

    fun getCryptoListFromLocal(): LiveData<List<ApiResponse>> {
        return localData.getDataFromLocal()
    }

    fun getCryptoListFromRemote(): List<ApiResponse>? {
        return remoteData.getDataFromRemote()
    }

    fun setCryptoListFromRemote(responseList: List<ApiResponse>?) = viewModelScope.launch {
        setLocal.setDataToLocal(responseList)
    }

    override fun onCleared() {
        super.onCleared()
        cryptoViewModel.cancel()
    }
}