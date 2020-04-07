package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse

/**
 * Created by SegunFrancis
 */
interface LocalSource {
    fun getLocalData(): LiveData<List<BaseResponse>>

    /**
     * Insert's response list into Room database
     * */
    suspend fun setLocalData(responseList: List<BaseResponse>?)
}