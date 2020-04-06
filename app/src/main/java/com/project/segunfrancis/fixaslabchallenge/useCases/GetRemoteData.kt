package com.project.segunfrancis.fixaslabchallenge.useCases

import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import retrofit2.Call

/**
 * Created by SegunFrancis
 */
class GetRemoteData(private val dataRepo: DataRepository) {
    fun getDataFromRemote(): Call<ResponseData> {
        return dataRepo.getRemoteData()
    }
}