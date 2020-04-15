package com.project.segunfrancis.crypto_info.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.project.segunfrancis.crypto_info.dataSource.DataRepository
import com.project.segunfrancis.crypto_info.dataSource.remote.ApiBuilder
import com.project.segunfrancis.crypto_info.dataSource.remote.ApiService
import com.project.segunfrancis.crypto_info.dataSource.local.CryptoDao
import com.project.segunfrancis.crypto_info.dataSource.local.CryptoRoomDatabase
import com.project.segunfrancis.crypto_info.dataSource.local.Local
import com.project.segunfrancis.crypto_info.dataSource.remote.Remote
import com.project.segunfrancis.crypto_info.model.BaseResponse
import com.project.segunfrancis.crypto_info.model.ResponseData
import com.project.segunfrancis.crypto_info.useCases.GetLocalData
import com.project.segunfrancis.crypto_info.useCases.GetRemoteData
import com.project.segunfrancis.crypto_info.useCases.SetLocalData
import com.project.segunfrancis.crypto_info.util.CryptoMediatorLiveData
import com.project.segunfrancis.crypto_info.util.Event
import com.project.segunfrancis.crypto_info.util.Resource
import com.project.segunfrancis.crypto_info.util.ResourceState
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by SegunFrancis
 */

class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    private val cryptoDao: CryptoDao = CryptoRoomDatabase.getDatabase(application).cryptoDao()
    private val apiService: ApiService = ApiBuilder.retrofit.create(
        ApiService::class.java
    )
    private val dataRepo: DataRepository
    private val localData: GetLocalData
    private val remoteData: GetRemoteData
    private val setLocal: SetLocalData
    val responseMessage = MutableLiveData<Event<Resource<ResponseData>>>()
    private val _searchQuery = MutableLiveData("")
    private val coins: LiveData<List<BaseResponse>>
    val isRefreshing = MutableLiveData<Event<Boolean>>()

    init {

        dataRepo =
            DataRepository(
                Local(cryptoDao), Remote(apiService)
            )
        localData = GetLocalData(dataRepo)
        remoteData = GetRemoteData(dataRepo)
        setLocal = SetLocalData(dataRepo)
        isRefreshing.value = Event(false)
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
            Event(
                Resource(
                    status = ResourceState.LOADING,
                    message = "Loading..."
                )
            )
        )
        remoteData.getDataFromRemote().enqueue(object : Callback<ResponseData?> {
            override fun onResponse(call: Call<ResponseData?>, response: Response<ResponseData?>) {
                responseMessage.postValue(
                    Event(
                        Resource(
                            status = ResourceState.SUCCESS,
                            message = "Success"
                        )
                    )
                )
                setCryptoListFromRemote(response.body()!!.data)
            }

            override fun onFailure(call: Call<ResponseData?>, t: Throwable) {
                responseMessage.postValue(
                    Event(
                        Resource(
                            status = ResourceState.ERROR,
                            message = "Check Network Connection"
                        )
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
}