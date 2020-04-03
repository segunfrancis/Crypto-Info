package com.project.segunfrancis.fixaslabchallenge.useCases

import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.ApiResponse

/**
 * Created by SegunFrancis
 */
class GetRemoteData(private val dataRepo: DataRepository) {
    fun getDataFromRemote(): List<ApiResponse>? {
        return dataRepo.getRemoteData()
    }
}