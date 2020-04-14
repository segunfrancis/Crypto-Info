package com.project.segunfrancis.fixaslabchallenge.useCases

import androidx.lifecycle.LiveData
import com.project.segunfrancis.fixaslabchallenge.dataSource.DataRepository
import com.project.segunfrancis.fixaslabchallenge.model.BaseResponse

/**
 * Created by SegunFrancis
 */
class GetLocalData(private val dataRepo: DataRepository) {

    fun getSearchResults(query: String?): LiveData<List<BaseResponse>> {
        return dataRepo.getSearchResults(query)
    }
}