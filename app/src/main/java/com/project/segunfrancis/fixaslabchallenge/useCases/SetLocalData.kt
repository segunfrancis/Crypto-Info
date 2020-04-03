package com.project.segunfrancis.fixaslabchallenge.useCases

import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse

/**
 * Created by SegunFrancis
 */
class SetLocalData(private val dataRepo: DataRepository) {
    suspend fun setDataToLocal(responseList: List<ApiResponse>?) {
        dataRepo.setLocalData(responseList)
    }
}