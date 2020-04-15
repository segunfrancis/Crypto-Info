package com.project.segunfrancis.crypto_info.dataSource

import androidx.lifecycle.LiveData
import com.project.segunfrancis.crypto_info.dataSource.local.LocalSource
import com.project.segunfrancis.crypto_info.dataSource.remote.RemoteSource
import com.project.segunfrancis.crypto_info.model.BaseResponse
import com.project.segunfrancis.crypto_info.model.ResponseData
import retrofit2.Call

/**
 * Created by SegunFrancis
 */
class DataRepository(private val local: LocalSource, private val remote: RemoteSource) {

    fun getRemoteData(): Call<ResponseData> {
        return remote.getRemoteData()
    }

    suspend fun setLocalData(responseList: List<BaseResponse>?) {
        local.setLocalData(responseList)
    }

    fun getSearchResults(query: String?): LiveData<List<BaseResponse>> {
        return local.getSearchResults("%$query%")
    }
}