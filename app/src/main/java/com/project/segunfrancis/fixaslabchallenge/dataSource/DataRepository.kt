package com.project.segunfrancis.fixaslabchallenge.dataSource

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.CryptoDao
import com.project.segunfrancis.fixaslabchallenge.dataSource.local.LocalSource
import com.project.segunfrancis.fixaslabchallenge.dataSource.remote.RemoteSource
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse

/**
 * Created by SegunFrancis
 */
class DataRepository(private val local: LocalSource, private val remote: RemoteSource) {
    fun getLocalData(): LiveData<List<ApiResponse>> {
        return local.getLocalData()
    }

    fun getRemoteData(): List<ApiResponse>? {
        return remote.getRemoteData()
    }

    suspend fun setLocalData(responseList: List<ApiResponse>?) {
        local.setLocalData(responseList)
    }
}