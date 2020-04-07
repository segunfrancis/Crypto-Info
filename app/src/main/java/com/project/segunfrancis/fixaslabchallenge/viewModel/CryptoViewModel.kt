package com.project.segunfrancis.fixaslabchallenge.viewModel

import android.app.Application
import android.util.Log
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
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import com.project.segunfrancis.fixaslabchallenge.useCases.GetLocalData
import com.project.segunfrancis.fixaslabchallenge.useCases.GetRemoteData
import com.project.segunfrancis.fixaslabchallenge.useCases.SetLocalData
import com.project.segunfrancis.fixaslabchallenge.util.Resource
import com.project.segunfrancis.fixaslabchallenge.util.ResourceState
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    val responseMessage = MutableLiveData<Resource<ResponseData>>()

    init {
        cryptoDao = CryptoRoomDatabase.getDatabase(application).cryptoDao()
        apiService = ApiBuilder.retrofit.create(
            ApiService::class.java
        )

        dataRepo = DataRepository(Local(cryptoDao), Remote(apiService))
        localData = GetLocalData(dataRepo)
        remoteData = GetRemoteData(dataRepo)
        setLocal = SetLocalData(dataRepo)
    }

    fun getCryptoListFromLocal(): LiveData<List<BaseResponse>> {
        return localData.getDataFromLocal()
    }

    fun getCryptoListFromRemote() {
        responseMessage.postValue(
            Resource(
                status = ResourceState.LOADING,
                message = "Loading..."
            )
        )
        remoteData.getDataFromRemote().enqueue(object : Callback<ResponseData?> {
            override fun onResponse(call: Call<ResponseData?>, response: Response<ResponseData?>) {
                responseMessage.postValue(
                    Resource(
                        status = ResourceState.SUCCESS,
                        message = "Success"
                    )
                )
                setCryptoListFromRemote(response.body()!!.data)
            }

            override fun onFailure(call: Call<ResponseData?>, t: Throwable) {
                responseMessage.postValue(
                    Resource(
                        status = ResourceState.ERROR,
                        message = "Check Network Connection"
                    )
                )
                Log.e("CryptoViewModel", t.localizedMessage!!)
            }
        })
    }

    fun setCryptoListFromRemote(responseList: List<BaseResponse>?) = viewModelScope.launch {
        setLocal.setDataToLocal(responseList)
    }

    override fun onCleared() {
        super.onCleared()
        cryptoViewModel.cancel()
    }
}