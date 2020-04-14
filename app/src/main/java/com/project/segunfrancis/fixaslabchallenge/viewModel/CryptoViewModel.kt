package com.project.segunfrancis.fixaslabchallenge.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
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
import com.project.segunfrancis.fixaslabchallenge.util.CryptoMediatorLiveData
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

    private val cryptoDao: CryptoDao = CryptoRoomDatabase.getDatabase(application).cryptoDao()
    private val apiService: ApiService = ApiBuilder.retrofit.create(
        ApiService::class.java
    )
    private val dataRepo: DataRepository
    private val localData: GetLocalData
    private val remoteData: GetRemoteData
    private val setLocal: SetLocalData
    val responseMessage = MutableLiveData<Resource<ResponseData>>()
    private val _searchQuery = MutableLiveData("")
    private val coins: LiveData<List<BaseResponse>>

    init {

        dataRepo = DataRepository(Local(cryptoDao), Remote(apiService))
        localData = GetLocalData(dataRepo)
        remoteData = GetRemoteData(dataRepo)
        setLocal = SetLocalData(dataRepo)

        getCryptoListFromRemote()

        coins = Transformations.switchMap(CryptoMediatorLiveData(_searchQuery)) {
            localData.getSearchResults(it)
        }
    }

    fun getCryptoListFromLocal(): LiveData<List<BaseResponse>> {
        return coins
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

    fun searchCoins(query: String?) {
        _searchQuery.value = query
    }

    override fun onCleared() {
        super.onCleared()
        cryptoViewModel.cancel()
    }
}