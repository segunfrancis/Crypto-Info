package com.project.segunfrancis.fixaslabchallenge.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.ApiBuilder
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.ApiService
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoRoomDatabase
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.Local
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.Remote
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.useCases.GetLocalData
import com.project.segunfrancis.fixaslabchallenge.useCases.GetRemoteData
import com.project.segunfrancis.fixaslabchallenge.useCases.SetLocalData
import kotlinx.coroutines.*

/**
 * Created by SegunFrancis
 */

class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    private val cryptoViewModel = Job()

    private val cryptoDao: CryptoDao
    private val apiService: ApiService
    private val dataRepo: DataRepository
    private val localData: GetLocalData
    private val remoteData: GetRemoteData
    private val setLocal: SetLocalData

    init {
        cryptoDao = CryptoRoomDatabase.getDatabase(application).cryptoDao()
        apiService = ApiBuilder.retrofit.create(
            ApiService::class.java
        )

        dataRepo = DataRepository(Local(cryptoDao), Remote(apiService, cryptoDao))
        localData = GetLocalData(dataRepo)
        remoteData = GetRemoteData(dataRepo)
        setLocal = SetLocalData(dataRepo)
    }

    fun getCryptoListFromLocal(): LiveData<List<BaseResponse>> {
        return localData.getDataFromLocal()
    }

    fun getCryptoListFromRemote() {
        remoteData.getDataFromRemote()
    }

    /*fun setCryptoListFromRemote(responseList: List<ApiResponse>?) = viewModelScope.launch {
        setLocal.setDataToLocal(responseList)
    }*/

    override fun onCleared() {
        super.onCleared()
        cryptoViewModel.cancel()
    }
}