package com.project.segunfrancis.fixaslabchallenge.useCases

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import com.project.segunfrancis.fixaslabchallenge.util.Resource

/**
 * Created by SegunFrancis
 */
class GetLocalData(private val dataRepo: DataRepository) {
    fun getDataFromLocal(): LiveData<Resource<List<BaseResponse>>?> {
        return dataRepo.getLocalData()
    }
}