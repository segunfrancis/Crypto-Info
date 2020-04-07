package com.project.segunfrancis.fixaslabchallenge.dataSource

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.LocalSource
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.RemoteSource
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import retrofit2.Call

/**
 * Created by SegunFrancis
 */
class DataRepository(private val local: LocalSource, private val remote: RemoteSource) {
    fun getLocalData(): LiveData<List<BaseResponse>> {
        return local.getLocalData()
    }

    fun getRemoteData(): Call<ResponseData> {
        return remote.getRemoteData()
    }

    suspend fun setLocalData(responseList: List<BaseResponse>?) {
        local.setLocalData(responseList)
    }
}