package com.project.segunfrancis.fixaslabchallenge.useCases

import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse

/**
 * Created by SegunFrancis
 */
class SetLocalData(private val dataRepo: DataRepository) {
    suspend fun setDataToLocal(responseList: List<BaseResponse>?) {
        dataRepo.setLocalData(responseList)
    }
}