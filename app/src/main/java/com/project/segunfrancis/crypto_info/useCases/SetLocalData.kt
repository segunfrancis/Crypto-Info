package com.project.segunfrancis.crypto_info.useCases

import com.project.segunfrancis.crypto_info.dataSource.DataRepository
import com.project.segunfrancis.crypto_info.model.BaseResponse

/**
 * Created by SegunFrancis
 */
class SetLocalData(private val dataRepo: DataRepository) {
    suspend fun setDataToLocal(responseList: List<BaseResponse>?) {
        dataRepo.setLocalData(responseList)
    }
}