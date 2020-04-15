package com.project.segunfrancis.crypto_info.useCases

import com.project.segunfrancis.crypto_info.dataSource.DataRepository
import com.project.segunfrancis.crypto_info.model.ResponseData
import retrofit2.Call

/**
 * Created by SegunFrancis
 */
class GetRemoteData(private val dataRepo: DataRepository) {
    fun getDataFromRemote(): Call<ResponseData> {
        return dataRepo.getRemoteData()
    }
}