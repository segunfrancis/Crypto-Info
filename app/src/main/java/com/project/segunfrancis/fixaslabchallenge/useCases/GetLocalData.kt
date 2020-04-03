package com.project.segunfrancis.fixaslabchallenge.useCases

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse

/**
 * Created by SegunFrancis
 */
class GetLocalData(private val dataRepo: DataRepository) {
    fun getDataFromLocal(): LiveData<List<ApiResponse>> {
        return dataRepo.getLocalData()
    }
}