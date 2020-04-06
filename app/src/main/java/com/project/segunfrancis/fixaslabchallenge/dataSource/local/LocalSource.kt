package com.project.segunfrancis.fixaslabchallenge.dataSource.local

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import com.project.segunfrancis.fixaslabchallenge.util.Resource

/**
 * Created by SegunFrancis
 */
interface LocalSource {
    fun getLocalData(): LiveData<Resource<List<BaseResponse>>?>

    /**
     * Insert's response list into Room database
     * */
    suspend fun setLocalData(responseList: Resource<List<BaseResponse>>?)
}