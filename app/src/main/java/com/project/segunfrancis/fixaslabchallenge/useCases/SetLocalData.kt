package com.project.segunfrancis.fixaslabchallenge.useCases

import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse
import com.project.segunfrancis.fixaslabchallenge.model.ResponseData
import com.project.segunfrancis.fixaslabchallenge.util.Resource

/**
 * Created by SegunFrancis
 */
class SetLocalData(private val dataRepo: DataRepository) {
    suspend fun setDataToLocal(responseList: Resource<List<BaseResponse>>?) {
        dataRepo.setLocalData(responseList)
    }
}